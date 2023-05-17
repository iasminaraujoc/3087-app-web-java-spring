package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.domain.filme.Filme;
import br.com.alura.screenmatch.domain.filme.FilmeRepository;
import br.com.alura.screenmatch.domain.genero.DadosAlteracaoGenero;
import br.com.alura.screenmatch.domain.genero.DadosCadastroGenero;
import br.com.alura.screenmatch.domain.genero.Genero;
import br.com.alura.screenmatch.domain.genero.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroRepository repository;

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model) {
        if (id != null) {
            var genero = repository.getReferenceById(id);
            model.addAttribute("genero", genero);
        }
        return "generos/formulario";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", repository.findAll());
        return "generos/listagem";
    }

    @PostMapping
    @Transactional
    public String cadastraGenero(DadosCadastroGenero dados) {
        var genero = new Genero(dados);

        repository.save(genero);

        return "redirect:/generos";
    }

    @PutMapping
    @Transactional
    public String alteraGenero(DadosAlteracaoGenero dados) {
        var genero = repository.getReferenceById(dados.id());
        genero.atualizaDados(dados);

        return "redirect:/generos";
    }

    @DeleteMapping
    @Transactional
    public String removeGenero(Long id) {
        repository.deleteById(id);

        return "redirect:/generos";
    }
}
