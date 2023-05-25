package br.com.alura.screenmatch.domain.filme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAlteracaoFilme(
        @NotNull
        Long id,
        @NotBlank
        String nome,

        @NotNull
        Integer duracao,

        @NotNull
        Integer ano,
        @NotNull
        Long idGenero) {
}
