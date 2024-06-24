package br.ada.caixa.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrarClientePJRequestDtoTest {
    @Test
    void testConstructor() {
        //given
        var expected1 = "SomeCNPJ";
        var expected2 = "SomeNomeFantasia";
        var expected3 = "SomeRazaoSocial";
        //when
        var actual = new RegistrarClientePJRequestDto(expected1, expected2, expected3);
        //then
        assertEquals(expected1, actual.getCnpj());
        assertEquals(expected2, actual.getNomeFantasia());
        assertEquals(expected3, actual.getRazaoSocial());
    }
}