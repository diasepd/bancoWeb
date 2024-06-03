package br.ada.caixa.service.conta;

import br.ada.caixa.entity.ContaPoupanca;
import br.ada.caixa.exceptions.ValidacaoException;
import br.ada.caixa.respository.ClienteRepository;
import br.ada.caixa.respository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public ContaPoupanca abrirContaPoupanca(String cpf) {
        //Regra: cliente PJ nao pode ter conta poupanca
        return clienteRepository.findByCpf(cpf)
                .map(clientePF -> {
                    var contaPoupanca = new ContaPoupanca();
                    contaPoupanca.setCliente(clientePF);
                    contaPoupanca.setSaldo(BigDecimal.ZERO);
                    return contaRepository.save(contaPoupanca);
                })
                .orElseThrow(() -> new ValidacaoException("Cliente nao encontrado com o CPF informado!"));
    }

}
