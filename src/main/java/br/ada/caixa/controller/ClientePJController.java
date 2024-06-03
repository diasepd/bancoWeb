package br.ada.caixa.controller;

import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.dto.response.SaldoResponseDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.service.cliente.ClientePJService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes/pj")
public class ClientePJController {

    private final ClientePJService clientePJService;

    public ClientePJController(ClientePJService clientePJService) {
        this.clientePJService = clientePJService;
    }

    @PostMapping
    public ResponseEntity<SaldoResponseDto> registrar(@RequestBody RegistrarClientePJRequestDto clienteDto) {
        var cliente = clientePJService.registrar(clienteDto);
        var contaNova = cliente.getContas().get(0);

        var saldoResponseDto = new SaldoResponseDto();
        saldoResponseDto.setNumeroConta(contaNova.getNumero());
        saldoResponseDto.setSaldo(contaNova.getSaldo());
        return ResponseEntity.status(HttpStatus.CREATED).body(saldoResponseDto);
    }

}
