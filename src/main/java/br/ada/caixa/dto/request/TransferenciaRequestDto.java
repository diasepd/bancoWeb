package br.ada.caixa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TransferenciaRequestDto {
    @NotNull
    private Long numeroContaOrigem;
    @NotNull
    private Long numeroContaDestino;
    @NotNull
    private BigDecimal valor;

    @Override
    public String toString() {
        return "TransfereRequestDto{" +
                "numeroContaOrigem='" + numeroContaOrigem + '\'' +
                ", numeroContaDestino='" + numeroContaDestino + '\'' +
                ", valor=" + valor +
                '}';
    }
}