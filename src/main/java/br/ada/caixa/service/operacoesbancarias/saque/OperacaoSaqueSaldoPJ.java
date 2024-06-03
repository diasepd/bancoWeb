package br.ada.caixa.service.operacoesbancarias.saque;

import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.entity.Conta;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Order(1)
@Service
public class OperacaoSaqueSaldoPJ implements OperacaoSaque {

    private static final BigDecimal TAXA_RETIRADA = BigDecimal.valueOf(1.005);

    @Override
    public void executar(Conta conta, BigDecimal valorSaque) {

        if (!(conta.getCliente() instanceof ClientePJ)) {
            return;
        }

        valorSaque = valorSaque.multiply(TAXA_RETIRADA);
        OperacaoSaque.super.validarSaldo(conta, valorSaque);
        conta.setSaldo(conta.getSaldo().subtract(valorSaque));
    }

}
