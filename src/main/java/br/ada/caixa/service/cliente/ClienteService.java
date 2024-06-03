package br.ada.caixa.service.cliente;

import br.ada.caixa.dto.response.ClienteResponseDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.respository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public List<ClienteResponseDto> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> {
            ClienteResponseDto clienteResponseDto = modelMapper.map(cliente, ClienteResponseDto.class);
            clienteResponseDto.setTipo(cliente.getTipo());
            return clienteResponseDto;
        }).collect(Collectors.toList());
    }

}
