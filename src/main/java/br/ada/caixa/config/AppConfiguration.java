package br.ada.caixa.config;

import br.ada.caixa.dto.request.RegistrarClientePFRequestDto;
import br.ada.caixa.dto.request.RegistrarClientePJRequestDto;
import br.ada.caixa.dto.response.ClientePFResponseDto;
import br.ada.caixa.dto.response.ClientePJResponseDto;
import br.ada.caixa.entity.ClientePF;
import br.ada.caixa.entity.ClientePJ;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper getModelMapper() {

        var modelMapper = new ModelMapper();

        modelMapper.typeMap(RegistrarClientePFRequestDto.class, ClientePF.class)
                .addMapping(RegistrarClientePFRequestDto::getCpf, ClientePF::setDocumento);

        modelMapper.typeMap(ClientePF.class, ClientePFResponseDto.class)
                        .addMapping(ClientePF::getDocumento, ClientePFResponseDto::setCpf);

        modelMapper.typeMap(RegistrarClientePJRequestDto.class, ClientePJ.class)
                .addMapping(RegistrarClientePJRequestDto::getCnpj, ClientePJ::setDocumento);

        modelMapper.typeMap(ClientePJ.class, ClientePJResponseDto.class)
                .addMapping(ClientePJ::getDocumento, ClientePJResponseDto::setCnpj);

        return modelMapper;
    }

}
