package br.ada.caixa.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePFResponseDto {
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private List<ContaResponseDto> contas;
}