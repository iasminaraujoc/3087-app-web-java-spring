package br.com.alura.screenmatch.domain.filme;

import br.com.alura.screenmatch.domain.genero.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByGeneroOrderByAnoLancamento(Genero genero);

    List<Filme> findByGeneroOrderByNome(Genero genero);
}
