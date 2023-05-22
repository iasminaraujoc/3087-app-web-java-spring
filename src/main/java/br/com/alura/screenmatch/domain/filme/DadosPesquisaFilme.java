package br.com.alura.screenmatch.domain.filme;

public record DadosPesquisaFilme(String nomePesquisa, String duracaoPesquisa, String anoPesquisa, Long idGenero) {

    public DadosCadastroFilme converteParaCadastro() {
        Integer anoInt = Integer.parseInt(anoPesquisa);
        Integer duracaoInt = Integer.parseInt(duracaoPesquisa.replaceAll("\\D+",""));

        var dadosCadastro = new DadosCadastroFilme(this.nomePesquisa, duracaoInt, anoInt, this.idGenero());

        return dadosCadastro;
    }
}
