package br.edu.projeto.model;

import java.time.LocalDateTime;

public class Senha {

    private final TipoSenha tipo;
    private final int numero;
    private final LocalDateTime horaEmissao;

    public Senha(TipoSenha tipo, int numero) {
        this.tipo = tipo;
        this.numero = numero;
        this.horaEmissao = LocalDateTime.now();
    }

    public Senha(TipoSenha tipo, int numero, LocalDateTime horaEmissao) {
        this.tipo = tipo;
        this.numero = numero;
        this.horaEmissao = horaEmissao;
    }

    public TipoSenha getTipo() {
        return tipo;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDateTime getHoraEmissao() {
        return horaEmissao;
    }

    @Override
    public String toString() {
        String prefixo = tipo == TipoSenha.COMUM ? "C" : "P";

        return String.format("%s-%03d", prefixo, numero);
    }
}
