
package br.edu.projeto.repository;

import br.edu.projeto.model.Senha;
import br.edu.projeto.model.TipoSenha;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoricoArquivoRepositoryTest {

    @TempDir
    Path diretorioTemporario;

    @Test
    void deveSalvarELerSenhasNaOrdemCorreta() {

        Path arquivo = diretorioTemporario.resolve("historico.csv");

        HistoricoRepository repositorio =
                new HistoricoArquivoRepository(arquivo);

        LocalDateTime horario1 = LocalDateTime.of(2026, 11, 18, 9, 0);
        LocalDateTime horario2 = LocalDateTime.of(2026, 11, 18, 9, 5);

        Senha senha1 = new Senha(TipoSenha.COMUM, 1, horario1);
        Senha senha2 = new Senha(TipoSenha.PREFERENCIAL, 1, horario2);

        repositorio.salvar(senha1);
        repositorio.salvar(senha2);

        List<Senha> historico = repositorio.listar();

        assertEquals(2, historico.size());

        assertEquals(TipoSenha.COMUM, historico.get(0).getTipo());
        assertEquals(1, historico.get(0).getNumero());
        assertEquals(horario1, historico.get(0).getHoraEmissao());

        assertEquals(TipoSenha.PREFERENCIAL, historico.get(1).getTipo());
        assertEquals(1, historico.get(1).getNumero());
        assertEquals(horario2, historico.get(1).getHoraEmissao());
    }

    @Test
    void deveRetornarListaVaziaQuandoArquivoNaoExistir() {

        Path arquivo = diretorioTemporario.resolve("inexistente.csv");

        HistoricoRepository repositorio =
                new HistoricoArquivoRepository(arquivo);

        List<Senha> historico = repositorio.listar();

        assertNotNull(historico);
        assertTrue(historico.isEmpty());
    }
}
