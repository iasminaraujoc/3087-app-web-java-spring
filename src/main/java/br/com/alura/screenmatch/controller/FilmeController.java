package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.filme.DadosAlteracaoFilme;
import br.com.alura.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.alura.screenmatch.domain.filme.Filme;
import br.com.alura.screenmatch.domain.filme.FilmeRepository;
import br.com.alura.screenmatch.domain.genero.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model) {
        model.addAttribute("generos", generoRepository.findAll());
        if (id != null) {
            var filme = repository.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", repository.findAll());
        return "filmes/listagem";
    }

    @GetMapping("/filtro")
    public String carregaPaginaFiltro(Model model, Long idGenero, boolean filtrarPorAno) {
        model.addAttribute("generos", generoRepository.findAll());

        if(idGenero != null){
            var genero = generoRepository.getReferenceById(idGenero);
            List<Filme> listaFiltrada;
            if(filtrarPorAno){
                listaFiltrada = repository.findByGeneroOrderByAnoLancamento(genero);
            } else{
                listaFiltrada = repository.findByGeneroOrderByNome(genero);
            }

            model.addAttribute("lista", listaFiltrada);
        }
        return "filmes/filtro";
    }

    @PostMapping
    @Transactional
    public String cadastraFilme(DadosCadastroFilme dados) {
        var genero = generoRepository.getReferenceById(dados.idGenero());
        var filme = new Filme(dados, genero);

        repository.save(filme);

        return "redirect:/filmes";
    }

    @PutMapping
    @Transactional
    public String alteraFilme(DadosAlteracaoFilme dados) {
        var genero = generoRepository.getReferenceById(dados.idGenero());
        var filme = repository.getReferenceById(dados.id());
        filme.atualizaDados(dados, genero);

        return "redirect:/filmes";
    }

    @DeleteMapping
    @Transactional
    public String removeFilme(Long id) {
        repository.deleteById(id);

        return "redirect:/filmes";
    }

}
