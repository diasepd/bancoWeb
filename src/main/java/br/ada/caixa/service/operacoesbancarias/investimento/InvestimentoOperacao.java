package br.ada.caixa.service.operacoesbancarias.investimento;

import br.ada.caixa.entity.ContaInvestimento;

import java.math.BigDecimal;

public interface InvestimentoOperacao {

    void executar(ContaInvestimento contaInvestimento, BigDecimal valor);

}
