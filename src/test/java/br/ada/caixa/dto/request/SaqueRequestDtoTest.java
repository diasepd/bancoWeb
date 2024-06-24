package br.ada.caixa.dto.request;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class SaqueRequestDtoTest {
    @Test
    void testConstructor() {
        //given
        long expected1 = 123456L;
        BigDecimal expected2 = BigDecimal.valueOf(100);
        //when
        var actual = new SaqueRequestDto(expected1, expected2);
        //then
        assertEquals(expected1, actual.getNumeroConta());
        assertEquals(expected2, actual.getValor());
    }
}