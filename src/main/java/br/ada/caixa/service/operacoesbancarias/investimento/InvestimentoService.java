package br.ada.caixa.service.operacoesbancarias.investimento;

import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.Conta;
import br.ada.caixa.entity.TipoCliente;
import br.ada.caixa.entity.TipoConta;
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

    public Conta investir(String documentoCliente, BigDecimal valor) {
        var cliente = clienteRepository.findByDocumento(documentoCliente);
        var contas = contaRepository.findContasByClienteAndTipo(cliente.get(), TipoConta.CONTA_INVESTIMENTO);
        if (contas.size() > 1) {
            throw new ValidacaoException("Cliente possui mais de uma conta investimento");
        }
        Conta contaInvestimento;
        if (contas.size() == 0) {
            contaInvestimento = new Conta();
            contaInvestimento.setTipo(TipoConta.CONTA_INVESTIMENTO);
            contaInvestimento.setCliente(cliente.get());
            contaInvestimento.setSaldo(BigDecimal.ZERO);
        } else {
            contaInvestimento = contas.get(0);
        }

        getOperacaoTipoCliente(cliente.get()).executar(contaInvestimento, valor);
        return contaRepository.save(contaInvestimento);
    }

    private InvestimentoOperacao getOperacaoTipoCliente(Cliente cliente) {
        if (cliente.getTipo().equals(TipoCliente.PF)) {
            return investimentoOperacaoPF;
        } else if (cliente.getTipo().equals(TipoCliente.PJ)) {
            return investimentoOperacaoPJ;
        } else {
            throw new ValidacaoException("Operacao investimento nao encontrada!");
        }
    }

}
