package br.com.alura.screenmatch.domain.filme;

public record DadosPesquisaFilme(String nomePesquisa, String duracaoPesquisa, String anoPesquisa, Long idGeneroPesquisa) {

    public DadosCadastroFilme converte() {

        int duracaoInt = Integer.valueOf(duracaoPesquisa.substring(0, 3));
        int anoInt = Integer.valueOf(anoPesquisa.substring(0, 4));

        return new DadosCadastroFilme(nomePesquisa, duracaoInt, anoInt, idGeneroPesquisa);
    }
}
