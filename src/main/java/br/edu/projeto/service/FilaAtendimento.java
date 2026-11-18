package br.edu.projeto.service;


import br.edu.projeto.model.Senha;
import br.edu.projeto.model.TipoSenha;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FilaAtendimento {
    private Queue<Senha> filaPreferencial;
    private Queue<Senha> filaComum;

    public FilaAtendimento() {
        filaPreferencial = new LinkedList<Senha>();
        filaComum = new LinkedList<Senha>();
    }

    public void adicionarSenha(Senha senha) {
        if (senha == null ) {
            throw  new IllegalArgumentException("Senha nula!");
        }
        senha.getTipo();

        if (senha.getTipo() == TipoSenha.PREFERENCIAL) {
            filaPreferencial.offer(senha);
        }else if (senha.getTipo() == TipoSenha.COMUM) {
            filaComum.offer(senha);
        }
    }

    public Senha chamarProxima() {
        if (!filaPreferencial.isEmpty()) {
            return filaPreferencial.poll();
        } else if (!filaComum.isEmpty()) {
            return filaComum.poll();
        }else{
            return null;
        }
    }

    public List<Senha> consultarFila(){
        List<Senha> todas =  new ArrayList<>();
        todas.addAll(filaPreferencial);
        todas.addAll(filaComum);
        return todas;
    }

    public boolean estaVazio(){
        return filaPreferencial.isEmpty() && filaComum.isEmpty();
    }
}
