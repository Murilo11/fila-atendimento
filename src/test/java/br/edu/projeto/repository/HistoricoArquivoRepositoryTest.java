package br.edu.projeto.repository;

import br.edu.projeto.model.Atendimento;
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
    void deveSalvarEListarAtendimentos() {

        Path arquivo = diretorioTemporario.resolve("historico.csv");
        HistoricoArquivoRepository repository =
                new HistoricoArquivoRepository(arquivo);

        LocalDateTime emissao = LocalDateTime.of(2026, 11, 18, 10, 0);
        LocalDateTime atendimento = LocalDateTime.of(2026, 11, 18, 10, 15);

        Senha senha1 = new Senha(TipoSenha.COMUM, 1, emissao);
        Senha senha2 = new Senha(TipoSenha.PREFERENCIAL, 1, emissao);

        repository.salvar(new Atendimento(senha1, atendimento));
        repository.salvar(new Atendimento(senha2, atendimento));

        List<Atendimento> historico = repository.listar();

        assertEquals(2, historico.size());

        assertEquals(TipoSenha.COMUM, historico.get(0).getSenha().getTipo());
        assertEquals(1, historico.get(0).getSenha().getNumero());
        assertEquals(emissao, historico.get(0).getSenha().getHoraEmissao());
        assertEquals(atendimento, historico.get(0).getHoraAtendimento());

        assertEquals(TipoSenha.PREFERENCIAL, historico.get(1).getSenha().getTipo());
        assertEquals(atendimento, historico.get(1).getHoraAtendimento());
    }

    @Test
    void deveRetornarListaVaziaQuandoArquivoNaoExistir() {

        Path arquivo = diretorioTemporario.resolve("inexistente.csv");
        HistoricoArquivoRepository repository =
                new HistoricoArquivoRepository(arquivo);

        List<Atendimento> historico = repository.listar();

        assertTrue(historico.isEmpty());
    }

    @Test
    void deveLimparHistorico() {

        Path arquivo = diretorioTemporario.resolve("historico.csv");
        HistoricoArquivoRepository repository =
                new HistoricoArquivoRepository(arquivo);

        Senha senha = new Senha(TipoSenha.COMUM, 1);
        repository.salvar(new Atendimento(senha));

        repository.limpar();

        assertTrue(repository.listar().isEmpty());
    }
}
