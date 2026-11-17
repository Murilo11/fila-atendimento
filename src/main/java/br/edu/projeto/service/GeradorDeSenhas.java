package br.edu.projeto.service;

import br.edu.projeto.model.TipoSenha;

public interface GeradorDeSenhas {
    int gerarSenha(TipoSenha tipo);
}
