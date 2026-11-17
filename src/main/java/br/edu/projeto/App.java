package br.edu.projeto;

import br.edu.projeto.model.TipoSenha;
import br.edu.projeto.service.GeradorDeSenhasImpl;

public class App {
    public static void main(String[] args) {


        GeradorDeSenhasImpl g1 = GeradorDeSenhasImpl.getInstance();
        GeradorDeSenhasImpl g2 = GeradorDeSenhasImpl.getInstance();
        System.out.println("Mesma instância? " + (g1 == g2));


        System.out.println("Comum: " + g1.gerarSenha(TipoSenha.COMUM));
        System.out.println("Comum: " + g1.gerarSenha(TipoSenha.COMUM));
        System.out.println("Comum: " + g1.gerarSenha(TipoSenha.COMUM));


        System.out.println("Preferencial: " + g1.gerarSenha(TipoSenha.PREFERENCIAL));
    }
}
