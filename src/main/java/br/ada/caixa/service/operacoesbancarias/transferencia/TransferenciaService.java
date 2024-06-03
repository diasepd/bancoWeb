package br.ada.caixa.service.operacoesbancarias.transferencia;

import br.ada.caixa.service.operacoesbancarias.deposito.DepositoService;
import br.ada.caixa.service.operacoesbancarias.saque.SaqueService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferenciaService {

    private final SaqueService saqueService;
    private final DepositoService depositoService;

    public TransferenciaService(SaqueService saqueService, DepositoService depositoService) {
        this.saqueService = saqueService;
        this.depositoService = depositoService;
    }

    public void transferir(Long numeroContaOrigem,
                           Long numeroContaDestino,
                           BigDecimal valor) {
        saqueService.sacar(numeroContaOrigem, valor);
        depositoService.depositar(numeroContaDestino, valor);
    }

}
