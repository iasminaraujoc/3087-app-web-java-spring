package br.com.alura.screenmatch.domain.genero;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAlteracaoGenero(@NotNull Long id, @NotBlank String nome) {
}
