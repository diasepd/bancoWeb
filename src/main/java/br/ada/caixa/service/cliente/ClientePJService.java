package br.ada.caixa.service.cliente;

import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.entity.Cliente;
import br.ada.caixa.entity.ClientePJ;
import br.ada.caixa.entity.ContaCorrente;
import br.ada.caixa.enums.StatusCliente;
import br.ada.caixa.respository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class ClientePJService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    public ClientePJService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public Cliente registrar(RegistrarClientePJRequestDto clienteDto) {
        ClientePJ cliente = modelMapper.map(clienteDto, ClientePJ.class);
        cliente.setStatus(StatusCliente.ATIVO);

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setCliente(cliente);
        contaCorrente.setSaldo(BigDecimal.ZERO);

        cliente.setContas(new ArrayList<>());
        cliente.getContas().add(contaCorrente);

        return clienteRepository.save(cliente);
    }

}
