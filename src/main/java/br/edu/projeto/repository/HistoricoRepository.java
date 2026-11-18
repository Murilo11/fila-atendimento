package br.edu.projeto.repository;

import br.edu.projeto.model.Atendimento;

import java.util.List;

public interface HistoricoRepository {

    void salvar(Atendimento atendimento);

    List<Atendimento> listar();

    void limpar();
}