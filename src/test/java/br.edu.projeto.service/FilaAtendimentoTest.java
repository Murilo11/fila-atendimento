package br.edu.projeto.service;

import br.edu.projeto.model.Senha;
import br.edu.projeto.model.TipoSenha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilaAtendimentoTest {

    private FilaAtendimento fila;

    @BeforeEach
    void setUp() {
        fila = new FilaAtendimento();
    }

    @Test
    void preferencialAntes(){
        Senha senha1 = new Senha(TipoSenha.COMUM,1);
        Senha senha2 = new Senha(TipoSenha.PREFERENCIAL,1);

        fila.adicionarSenha(senha1);
        fila.adicionarSenha(senha2);

        Senha primeiraChamada = fila.chamarProxima();
        Senha segundaChamada = fila.chamarProxima();

        assertEquals(TipoSenha.PREFERENCIAL, primeiraChamada.getTipo());
        assertEquals(TipoSenha.COMUM, segundaChamada.getTipo());

    }

    @Test
    void respeitarFIFO(){
        fila.adicionarSenha(new Senha(TipoSenha.COMUM,1));
        fila.adicionarSenha(new Senha(TipoSenha.COMUM,2));


        Senha s1 = fila.chamarProxima();
        Senha s2 = fila.chamarProxima();

        assertEquals(1,s1.getNumero());
        assertEquals(2,s2.getNumero());

    }
    @Test
    void nullQuandoVazia(){
        assertTrue(fila.estaVazio());
        assertNull(fila.chamarProxima());
    }

    @Test
    void consultarSemRemover(){
        Senha senhaComum = new Senha(TipoSenha.COMUM,1);
        Senha senhaPref = new Senha(TipoSenha.PREFERENCIAL,1);

        fila.adicionarSenha(senhaComum);
        fila.adicionarSenha(senhaPref);

        List<Senha> lista =  fila.consultarFila();
        assertEquals(2,lista.size());
        assertEquals(TipoSenha.PREFERENCIAL,lista.get(0).getTipo());
        assertFalse(fila.estaVazio());
    }

    @Test
    void rejeitarSenhaNula(){
        assertThrows(IllegalArgumentException.class, () -> {
            fila.adicionarSenha(null);
        });
    }
}