package br.ada.caixa.dto.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RegistrarClientePFRequestDtoTest {
    @Test
    void testConstructor() {
        //given
        var expected1 = "SomeCPF";
        var expected2 = "SomeNome";
        LocalDate expected3 = LocalDate.now();
        //when
        var actual = new RegistrarClientePFRequestDto(expected1, expected2, expected3);
        //then
        assertEquals(expected1, actual.getCpf());
        assertEquals(expected2, actual.getNome());
        assertEquals(expected3, actual.getDataNascimento());
    }
}