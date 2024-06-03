package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistrarClientePFRequestDto {

    private String cpf;
    private String nome;
    private LocalDate dataNascimento;

}
