package br.ada.caixa.dto.request;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class InvestimentoRequestDtoTest {
    @Test
    void testConstructor() {
        //given
        var expected1 = "123456L";
        BigDecimal expected2 = BigDecimal.valueOf(100);
        //when
        var actual = new InvestimentoRequestDto(expected1, expected2);
        //then
        assertEquals(expected1, actual.getDocumentoCliente());
        assertEquals(expected2, actual.getValor());
    }
}