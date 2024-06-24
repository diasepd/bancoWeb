package br.ada.caixa.dto.response;

import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaResponseDto {
    private String numero;
    private BigDecimal saldo;
    private String tipo;
}