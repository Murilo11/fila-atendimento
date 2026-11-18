
package br.edu.projeto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SenhaTest {

    @Test
    void deveFormatarSenhaComumCorretamente() {
        Senha senha = new Senha(TipoSenha.COMUM, 1);

        assertEquals("C-001", senha.toString());
    }

    @Test
    void deveFormatarSenhaPreferencialCorretamente() {
        Senha senha = new Senha(TipoSenha.PREFERENCIAL, 1);

        assertEquals("P-001", senha.toString());
    }
}
