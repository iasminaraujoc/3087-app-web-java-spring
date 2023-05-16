package br.com.alura.screenmatch.domain.filme;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByGeneroNome(String nome);
}
