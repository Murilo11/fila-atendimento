package br.edu.projeto;

import br.edu.projeto.cli.MenuAtendimento;
import br.edu.projeto.repository.HistoricoArquivoRepository;
import br.edu.projeto.repository.HistoricoRepository;
import br.edu.projeto.service.FilaAtendimento;
import br.edu.projeto.service.GeradorDeSenhas;
import br.edu.projeto.service.GeradorDeSenhasImpl;

public class App {

    public static void main(String[] args) {

        GeradorDeSenhas gerador = GeradorDeSenhasImpl.getInstance();

        HistoricoRepository historico = new HistoricoArquivoRepository();

        FilaAtendimento fila = new FilaAtendimento();

        MenuAtendimento menu = new MenuAtendimento(gerador, historico, fila);

        menu.iniciar();
    }
}
