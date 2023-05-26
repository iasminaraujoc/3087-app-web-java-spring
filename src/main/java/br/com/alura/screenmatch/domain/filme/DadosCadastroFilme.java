package br.com.alura.screenmatch.domain.filme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DadosCadastroFilme{


        @NotBlank(message = "O nome do filme não pode estar em branco")
        String nome;
        @NotNull(message = "A duração do filme não pode estar em branco e deve ser um número")
        Integer duracao;
        @NotNull(message = "O ano do filme não pode estar em branco e deve ser um número")
        Integer ano;
        @NotNull(message = "O gênero do filme deve ser selecionado")
        Long idGenero;

        public DadosCadastroFilme(String nome, Integer duracao, Integer ano, Long idGenero) {
                this.nome = nome;
                this.duracao = duracao;
                this.ano = ano;
                this.idGenero = idGenero;
        }

        public DadosCadastroFilme(){}

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public Integer getDuracao() {
                return duracao;
        }

        public void setDuracao(Integer duracao) {
                this.duracao = duracao;
        }

        public Integer getAno() {
                return ano;
        }

        public void setAno(Integer ano) {
                this.ano = ano;
        }

        public Long getIdGenero() {
                return idGenero;
        }

        public void setIdGenero(Long idGenero) {
                this.idGenero = idGenero;
        }
}
