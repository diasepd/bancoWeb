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
public class InvestimentoRequestDto {
    @NotNull
    private String documentoCliente;
    @NotNull
    private BigDecimal valor;

    @Override
    public String toString() {
        return "InvestimentoRequestDto{" +
                "documentoCliente='" + documentoCliente + '\'' +
                ", valor=" + valor +
                '}';
    }
}