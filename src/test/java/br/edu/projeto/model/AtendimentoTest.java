
package br.edu.projeto.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AtendimentoTest {

    @Test
    void deveArmazenarSenhaEHorarioDoAtendimento() {

        Senha senha = new Senha(TipoSenha.COMUM, 1);
        LocalDateTime horario = LocalDateTime.of(2026, 11, 18, 10, 30);

        Atendimento atendimento = new Atendimento(senha, horario);

        assertSame(senha, atendimento.getSenha());
        assertEquals(horario, atendimento.getHoraAtendimento());
    }

    @Test
    void deveExibirInformacoesNoToString() {

        Senha senha = new Senha(TipoSenha.PREFERENCIAL, 1);
        LocalDateTime horario = LocalDateTime.of(2026, 11, 18, 10, 30);

        Atendimento atendimento = new Atendimento(senha, horario);

        assertTrue(atendimento.toString().contains(senha.toString()));
        assertTrue(atendimento.toString().contains("Atendida em:"));
    }
}
