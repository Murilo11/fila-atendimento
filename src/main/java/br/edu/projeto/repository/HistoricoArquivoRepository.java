package br.edu.projeto.repository;

import br.edu.projeto.model.Senha;
import br.edu.projeto.model.TipoSenha;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoricoArquivoRepository implements HistoricoRepository {

    private static final String CABECALHO = "tipo,numero,horaEmissao";

    private final Path caminhoArquivo;

    public HistoricoArquivoRepository() {
        this(Path.of("historico.csv"));
    }

    public HistoricoArquivoRepository(Path caminhoArquivo) {
        this.caminhoArquivo = Objects.requireNonNull(caminhoArquivo);
    }

    @Override
    public void salvar(Senha senha) {

        Objects.requireNonNull(senha, "A senha não pode ser nula.");

        try {
            Path diretorio = caminhoArquivo.getParent();

            if (diretorio != null) {
                Files.createDirectories(diretorio);
            }

            boolean arquivoVazio = !Files.exists(caminhoArquivo)
                    || Files.size(caminhoArquivo) == 0;

            String registro = senha.getTipo().name() + ","
                    + senha.getNumero() + ","
                    + senha.getHoraEmissao() + System.lineSeparator();

            if (arquivoVazio) {
                registro = CABECALHO + System.lineSeparator() + registro;
            }

            Files.writeString(
                    caminhoArquivo,
                    registro,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {
            throw new UncheckedIOException("Erro ao salvar o histórico.", e);
        }
    }

    @Override
    public List<Senha> listar() {

        List<Senha> historico = new ArrayList<>();

        if (!Files.exists(caminhoArquivo)) {
            return historico;
        }

        try {
            List<String> linhas = Files.readAllLines(
                    caminhoArquivo,
                    StandardCharsets.UTF_8
            );

            for (String linha : linhas) {

                if (linha.isBlank() || linha.equals(CABECALHO)) {
                    continue;
                }

                String[] dados = linha.split(",", -1);

                if (dados.length != 3) {
                    throw new IllegalStateException(
                            "Registro inválido no histórico: " + linha
                    );
                }

                TipoSenha tipo = TipoSenha.valueOf(dados[0]);
                int numero = Integer.parseInt(dados[1]);
                LocalDateTime horaEmissao = LocalDateTime.parse(dados[2]);

                Senha senha = new Senha(tipo, numero, horaEmissao);

                historico.add(senha);
            }

        } catch (IOException e) {
            throw new UncheckedIOException("Erro ao ler o histórico.", e);
        }

        return historico;
    }
}