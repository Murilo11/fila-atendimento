package br.edu.projeto.cli;

import br.edu.projeto.model.Senha;
import br.edu.projeto.model.TipoSenha;
import br.edu.projeto.repository.HistoricoRepository;
import br.edu.projeto.service.GeradorDeSenhas;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuAtendimento {

    private final Scanner scanner;
    private final GeradorDeSenhas gerador;
    private final HistoricoRepository historico;

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public MenuAtendimento(
            GeradorDeSenhas gerador,
            HistoricoRepository historico
    ) {
        this.scanner = new Scanner(System.in);
        this.gerador = gerador;
        this.historico = historico;
    }

    public void iniciar() {

        boolean executando = true;

        while (executando) {

            exibirMenu();

            System.out.print("Escolha uma opção: ");
            String entrada = scanner.nextLine();

            try {
                int opcao = Integer.parseInt(entrada);

                executando = executarOpcao(opcao);

            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
            }
        }
    }

    private void exibirMenu() {

        System.out.println("\n====================================");
        System.out.println("     SISTEMA DE FILA DE ATENDIMENTO");
        System.out.println("====================================");
        System.out.println("1 - Gerar senha comum");
        System.out.println("2 - Gerar senha preferencial");
        System.out.println("3 - Chamar próxima senha");
        System.out.println("4 - Consultar fila");
        System.out.println("5 - Consultar histórico");
        System.out.println("0 - Sair");
        System.out.println("====================================");
    }

    private boolean executarOpcao(int opcao) {

        switch (opcao) {

            case 1 -> gerarSenha(TipoSenha.COMUM);

            case 2 -> gerarSenha(TipoSenha.PREFERENCIAL);

            case 3 -> System.out.println("Funcionalidade aguardando integração com a fila.");

            case 4 -> System.out.println("Funcionalidade aguardando integração com a fila.");

            case 5 -> consultarHistorico();

            case 0 -> {
                System.out.println("Encerrando o sistema. Até mais!");
                return false;
            }

            default -> System.out.println("Opção inválida. Escolha uma opção do menu.");
        }

        return true;
    }

    private void gerarSenha(TipoSenha tipo) {

        int numero = gerador.gerarSenha(tipo);
        Senha senha = new Senha(tipo, numero);

        System.out.println("\nSenha gerada: " + senha);
    }

    private void consultarHistorico() {

        List<Senha> senhas = historico.listar();

        if (senhas.isEmpty()) {
            System.out.println("\nNenhuma senha registrada no histórico.");
            return;
        }

        System.out.println("\n========== HISTÓRICO ==========");

        for (Senha senha : senhas) {
            System.out.printf(
                    "%s | Emitida em: %s%n",
                    senha,
                    senha.getHoraEmissao().format(FORMATO_DATA)
            );
        }
    }
}
