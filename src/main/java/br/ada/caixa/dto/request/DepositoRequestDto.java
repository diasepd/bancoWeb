package br.ada.caixa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositoRequestDto {
    @NotNull
    private Long numeroConta;
    @NotNull
    private BigDecimal valor;
    @Override
    public String toString() {
        return "DepositoRequestDto{" +
                "numeroConta='" + numeroConta + '\'' +
                ", valor=" + valor +
                '}';
    }
}