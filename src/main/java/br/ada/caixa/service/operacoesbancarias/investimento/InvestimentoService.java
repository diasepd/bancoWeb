package br.ada.caixa.service.operacoesbancarias.investimento;

import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.entity.ContaInvestimento;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.respository.ClienteRepository;
import br.ada.caixa.respository.ContaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InvestimentoService {

    @Qualifier("investimentoOperacaoPF")
    private final InvestimentoOperacao investimentoOperacaoPF;
    @Qualifier("investimentoOperacaoPJ")
    private final InvestimentoOperacao investimentoOperacaoPJ;

    private final ContaRepository contaRepository;
    public final ClienteRepository clienteRepository;

    public InvestimentoService(InvestimentoOperacao investimentoOperacaoPF, InvestimentoOperacao investimentoOperacaoPJ, ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.investimentoOperacaoPF = investimentoOperacaoPF;
        this.investimentoOperacaoPJ = investimentoOperacaoPJ;
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public ContaInvestimento investir(String documentoCliente, BigDecimal valor) {
        var contaInvestimento = contaRepository.findContaInvestimentoPorCliente(documentoCliente)
                .orElseGet(() -> {
                    var contaInvestimentoNova = new ContaInvestimento();
                    contaInvestimentoNova.setCliente(clienteRepository.findById(documentoCliente).get());
                    contaInvestimentoNova.setSaldo(BigDecimal.ZERO);
                    return contaRepository.save(contaInvestimentoNova);
                });

        getOperacaoTipoCliente(contaInvestimento.getCliente()).executar(contaInvestimento, valor);
        return contaRepository.save(contaInvestimento);
    }

    private InvestimentoOperacao getOperacaoTipoCliente(Cliente cliente) {
        if (cliente instanceof ClientePF) {
            return investimentoOperacaoPF;
        } else if (cliente instanceof ClientePJ) {
            return investimentoOperacaoPJ;
        } else {
            throw new ValidacaoException("Operacao investimento nao encontrada!");
        }
    }

}
