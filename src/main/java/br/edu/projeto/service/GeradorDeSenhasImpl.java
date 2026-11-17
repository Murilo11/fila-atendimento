package br.edu.projeto.service;

import br.edu.projeto.model.TipoSenha;

import java.util.concurrent.atomic.AtomicInteger;

public class GeradorDeSenhasImpl implements GeradorDeSenhas {

    private final AtomicInteger contadorComum = new AtomicInteger(0);
    private final AtomicInteger contadorPreferencial= new AtomicInteger(0);

    private GeradorDeSenhasImpl() {
    }

    private static class Holder{
        private static final GeradorDeSenhasImpl INSTANCIA =  new GeradorDeSenhasImpl();
    }

    public static GeradorDeSenhasImpl getInstance(){
        return Holder.INSTANCIA;
    }

    @Override
    public int gerarSenha(TipoSenha tipo) {
        return tipo == TipoSenha.COMUM ? contadorComum.incrementAndGet() : contadorPreferencial.incrementAndGet();

    }



}

