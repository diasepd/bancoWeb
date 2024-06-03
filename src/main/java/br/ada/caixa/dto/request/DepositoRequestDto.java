package br.ada.caixa.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
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
