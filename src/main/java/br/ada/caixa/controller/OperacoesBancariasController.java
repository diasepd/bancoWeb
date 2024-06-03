package br.ada.caixa.controller;

import br.ada.caixa.dto.request.DepositoRequestDto;
import br.ada.caixa.dto.request.InvestimentoRequestDto;
import br.ada.caixa.dto.request.SaqueRequestDto;
import br.ada.caixa.dto.request.TransferenciaRequestDto;
import br.ada.caixa.dto.response.SaldoResponseDto;
import br.ada.caixa.service.conta.ContaService;
import br.ada.caixa.service.operacoesbancarias.deposito.DepositoService;
import br.ada.caixa.service.operacoesbancarias.investimento.InvestimentoService;
import br.ada.caixa.service.operacoesbancarias.saldo.SaldoService;
import br.ada.caixa.service.operacoesbancarias.saque.SaqueService;
import br.ada.caixa.service.operacoesbancarias.transferencia.TransferenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operacoes")
public class OperacoesBancariasController {

    private final DepositoService depositoService;
    private final SaqueService saqueService;
    private final TransferenciaService transferenciaService;
    private final SaldoService saldoService;
    private final InvestimentoService investimentoService;
    private final ContaService contaService;

    public OperacoesBancariasController(DepositoService depositoService, SaqueService saqueService, TransferenciaService transferenciaService, SaldoService saldoService, InvestimentoService investimentoService, ContaService contaService) {
        this.depositoService = depositoService;
        this.saqueService = saqueService;
        this.transferenciaService = transferenciaService;
        this.saldoService = saldoService;
        this.investimentoService = investimentoService;
        this.contaService = contaService;
    }

    @PostMapping("/deposito")
    public ResponseEntity<?> depositar(@RequestBody DepositoRequestDto depositoRequestDto) {
        depositoService.depositar(depositoRequestDto.getNumeroConta(), depositoRequestDto.getValor());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saque")
    public ResponseEntity<?> sacar(@RequestBody SaqueRequestDto saqueRequestDto) {
        saqueService.sacar(saqueRequestDto.getNumeroConta(), saqueRequestDto.getValor());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transferencia")
    public void transferencia(@RequestBody TransferenciaRequestDto transferenciaRequestDto) {
        transferenciaService.transferir(transferenciaRequestDto.getNumeroContaOrigem(),
                transferenciaRequestDto.getNumeroContaDestino(),
                transferenciaRequestDto.getValor());
    }

    @GetMapping("/saldo/{numeroConta}")
    public ResponseEntity<SaldoResponseDto> consultarSaldo(@PathVariable Long numeroConta) {
        var saldoResponseDto = new SaldoResponseDto();
        saldoResponseDto.setNumeroConta(numeroConta);
        saldoResponseDto.setSaldo(saldoService.consultarSaldo(numeroConta));
        return ResponseEntity.status(HttpStatus.OK).body(saldoResponseDto);
    }

    @PostMapping("/investimento")
    public ResponseEntity<SaldoResponseDto> investir(@RequestBody InvestimentoRequestDto investimentoRequestDto) {
        var contaInvestimento = investimentoService.investir(investimentoRequestDto.getDocumentoCliente(), investimentoRequestDto.getValor());

        var saldoResponseDto = new SaldoResponseDto();
        saldoResponseDto.setNumeroConta(contaInvestimento.getNumero());
        saldoResponseDto.setSaldo(contaInvestimento.getSaldo());
        return ResponseEntity.status(HttpStatus.OK).body(saldoResponseDto);
    }

    //Regra: cliente PJ nao pode ter conta poupanca
    @PostMapping("/abrir-conta-poupanca/{cpf}")
    public ResponseEntity<SaldoResponseDto> abrirContaPoupanca(@PathVariable String cpf) {
        var contaPoupanca = contaService.abrirContaPoupanca(cpf);

        var saldoResponseDto = new SaldoResponseDto();
        saldoResponseDto.setNumeroConta(contaPoupanca.getNumero());
        saldoResponseDto.setSaldo(contaPoupanca.getSaldo());
        return ResponseEntity.status(HttpStatus.OK).body(saldoResponseDto);
    }

}
