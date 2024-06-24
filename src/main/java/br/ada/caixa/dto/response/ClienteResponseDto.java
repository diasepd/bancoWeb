package br.ada.caixa.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDto {
    private String documento;
    private String tipo;
}