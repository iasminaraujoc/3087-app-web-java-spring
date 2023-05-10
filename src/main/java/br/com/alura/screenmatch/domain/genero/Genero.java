package br.com.alura.screenmatch.domain.genero;

import jakarta.persistence.*;

@Entity
@Table(name="generos")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Genero(DadosCadastroGenero dados) {
        this.nome = dados.nome();
    }

    public Genero(){}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void atualizaDados(DadosAlteracaoGenero dados) {
        this.nome = dados.nome();
    }
}
