package br.ada.caixa.dto.request;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TransferenciaRequestDtoTest {
    @Test
    void testConstructor() {
        //given
        long expected1 = 123456L;
        long expected2 = 123454L;
        BigDecimal expected3 = BigDecimal.valueOf(100);
        //when
        var actual = new TransferenciaRequestDto(expected1, expected2, expected3);
        //then
        assertEquals(expected1, actual.getNumeroContaOrigem());
        assertEquals(expected2, actual.getNumeroContaDestino());
        assertEquals(expected3, actual.getValor());
    }
}