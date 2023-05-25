package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.filme.*;
import br.com.alura.screenmatch.domain.genero.Genero;
import br.com.alura.screenmatch.domain.genero.GeneroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
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

    @GetMapping("/filtro")
    public String carregaPaginaListagem(Model model, Long idGenero, boolean filtrarPorAno) {
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

    @GetMapping("/pesquisa")
    public String carregaPaginaPesquisaOMDB(Model model){
        model.addAttribute("generos", generoRepository.findAll());
        return "filmes/pesquisa";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("generos", generoRepository.findAll());
        model.addAttribute("lista", repository.findAll());
        return "filmes/listagem";
    }

    @PostMapping
    @Transactional
    public String cadastraFilme(@Valid DadosCadastroFilme dados, DadosPesquisaFilme dadosPesquisa) {
        Genero genero;
        if(dadosPesquisa != null){
            genero = generoRepository.getReferenceById(dadosPesquisa.idGenero());
            dados = dadosPesquisa.converteParaCadastro();
        } else {
            genero = generoRepository.getReferenceById(dados.idGenero());
        }

        var filme = new Filme(dados, genero);

        repository.save(filme);

        return "redirect:/filmes";
    }

    @PutMapping
    @Transactional
    public String alteraFilme(@Valid DadosAlteracaoFilme dados) {
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
