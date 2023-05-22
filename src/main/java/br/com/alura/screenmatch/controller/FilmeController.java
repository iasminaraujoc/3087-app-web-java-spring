package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.filme.*;
import br.com.alura.screenmatch.domain.genero.GeneroRepository;
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
    public String carregaPaginaListagem(Model model, Long idGenero, String tipoOrdenacao) {
        model.addAttribute("generos", generoRepository.findAll());

        if(idGenero != null){
            var genero = generoRepository.getReferenceById(idGenero);
            List<Filme> filmesPorGenero =  repository.findByGenero(genero);

            if(tipoOrdenacao != null){
                switch(tipoOrdenacao){
                    case "nome":
                        filmesPorGenero = filmesPorGenero.stream().sorted(Comparator.comparing(Filme::getNome)).toList();
                        break;
                    case "ano":
                        filmesPorGenero = filmesPorGenero.stream().sorted(Comparator.comparing(Filme::getAnoLancamento)).toList();
                        break;
                    case "duracao":
                        filmesPorGenero = filmesPorGenero.stream().sorted(Comparator.comparing(Filme::getDuracaoEmMinutos)).toList();
                        break;
                    case "original":
                        break;
                }
            }
            model.addAttribute("listaPorGenero", filmesPorGenero);
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

    @PostMapping("/pesquisa")
    @Transactional
    public String pesquisaFilme(DadosPesquisaFilme dadosPesquisa) { //trata os dados da pesquisa
        var genero = generoRepository.getReferenceById(dadosPesquisa.idGenero());
        DadosCadastroFilme dadosCadastro = dadosPesquisa.converteParaCadastro();
        var filme = new Filme(dadosCadastro, genero);

        repository.save(filme);

        return "redirect:/filmes";
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
