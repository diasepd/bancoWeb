package br.ada.caixa.service.cliente;

import br.ada.caixa.dto.request.RegistrarClientePFRequestDto;
import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.dto.response.RegistrarClienteResponseDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.TipoCliente;
import br.ada.caixa.respository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ClienteServiceTest {
    private ClienteRepository clienteRepository = mock(ClienteRepository.class);
    private ModelMapper modelMapper = mock(ModelMapper.class);
    private ClienteService service = new ClienteService(clienteRepository, modelMapper);

    @Test
    void registrarPFTest() {
        //given
        final RegistrarClientePFRequestDto clienteDto = mock(RegistrarClientePFRequestDto.class);
        final var cliente = mock(Cliente.class);

        given(modelMapper.map(clienteDto, Cliente.class)).willReturn(cliente);
        given(clienteRepository.save(cliente)).willReturn(cliente);

        //when
        RegistrarClienteResponseDto actual = service.registrarPF(clienteDto);

        //then
        verify(clienteRepository).save(cliente);
        assertNotNull(actual.getSaldoResponseDto());
    }

    @Test
    void registrarPJTest() {
        //given
        final RegistrarClientePJRequestDto clienteDto = mock(RegistrarClientePJRequestDto.class);
        final var cliente = mock(Cliente.class);

        given(modelMapper.map(clienteDto, Cliente.class)).willReturn(cliente);
        given(clienteRepository.save(cliente)).willReturn(cliente);

        //when
        RegistrarClienteResponseDto actual = service.registrarPJ(clienteDto);

        //then
        verify(clienteRepository).save(cliente);
        assertNotNull(actual.getSaldoResponseDto());
    }


    @Test
    void listarTodosPFTest() {
        //given
        final var cliente = mock(Cliente.class);
        final ClienteResponseDto clienteResponseDto = mock(ClienteResponseDto.class);

        final TipoCliente tipoCliente = TipoCliente.PF;
        final List<Cliente> clientes = mock(List.class);
        final List<ClienteResponseDto> expected = List.of(clienteResponseDto);

        given(clienteRepository.findAllByTipo(tipoCliente)).willReturn(clientes);
        given(modelMapper.map(cliente, ClienteResponseDto.class)).willReturn(clienteResponseDto);

        //when
        List<ClienteResponseDto> actual = service.listarTodos(TipoCliente.PF);

        //then
        assertEquals(expected, actual);
    }
}