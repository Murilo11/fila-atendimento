
package br.edu.projeto.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Atendimento {

    private final Senha senha;
    private final LocalDateTime horaAtendimento;

    public Atendimento(Senha senha) {
        this.senha = Objects.requireNonNull(senha, "A senha não pode ser nula.");
        this.horaAtendimento = LocalDateTime.now();
    }

    public Atendimento(Senha senha, LocalDateTime horaAtendimento) {
        this.senha = Objects.requireNonNull(senha, "A senha não pode ser nula.");
        this.horaAtendimento = Objects.requireNonNull(
                horaAtendimento,
                "O horário do atendimento não pode ser nulo."
        );
    }

    public Senha getSenha() {
        return senha;
    }

    public LocalDateTime getHoraAtendimento() {
        return horaAtendimento;
    }

    @Override
    public String toString() {
        return senha + " | Atendida em: " + horaAtendimento;
    }
}
