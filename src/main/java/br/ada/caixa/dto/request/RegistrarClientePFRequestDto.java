package br.ada.caixa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarClientePFRequestDto {
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
}