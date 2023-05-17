package br.com.alura.screenmatch.domain.filme;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    @Query("SELECT new br.com.alura.screenmatch.domain.filme.Consulta(f.genero.nome, COUNT(f)) FROM Filme f GROUP BY f.genero.nome")
    List<Consulta> encontraQtdeFilmesPorGenero();
}
