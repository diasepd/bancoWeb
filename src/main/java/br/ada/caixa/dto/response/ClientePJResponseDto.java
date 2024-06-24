package br.ada.caixa.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePJResponseDto {
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
}