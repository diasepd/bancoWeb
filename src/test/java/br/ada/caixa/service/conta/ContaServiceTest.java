package br.ada.caixa.service.conta;

import br.ada.caixa.entity.Conta;
import br.ada.caixa.respository.ClienteRepository;
import br.ada.caixa.respository.ContaRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ContaServiceTest {
    private ContaRepository contaRepository = mock(ContaRepository.class);
    private ClienteRepository clienteRepository = mock(ClienteRepository.class);
    private ContaService service = new ContaService(contaRepository, clienteRepository);

    @Test
    void abrirContaPoupancaTest() {
        //given
        final String cpf = "91696232791";
        Conta expected = mock(Conta.class);
        Conta contaPoupanca = new Conta();
        given(contaRepository.save(contaPoupanca)).willReturn(expected);

        //when
        Conta actual = service.abrirContaPoupanca(cpf);

        //then
    }
}