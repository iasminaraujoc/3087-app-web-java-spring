package br.com.alura.screenmatch.domain.genero;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroGenero(@NotBlank String nome) {
}
