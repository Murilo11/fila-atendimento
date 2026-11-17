package br.edu.projeto.repository;

import br.edu.projeto.model.Senha;

import java.util.List;

public interface HistoricoRepository {

    void salvar(Senha senha);

    List<Senha> listar();

}