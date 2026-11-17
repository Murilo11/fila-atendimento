package br.edu.projeto;

import br.edu.projeto.model.Senha;
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

        System.out.println("\n-------------------------------------");
        System.out.println("         Teste classe senha            \n");

        Senha senha1 = new Senha(TipoSenha.COMUM, 1);
        Senha senha2 = new Senha(TipoSenha.PREFERENCIAL, 1);

        System.out.println(senha1);
        System.out.println(senha2);

        System.out.println(senha1.getHoraEmissao());
    }
}
