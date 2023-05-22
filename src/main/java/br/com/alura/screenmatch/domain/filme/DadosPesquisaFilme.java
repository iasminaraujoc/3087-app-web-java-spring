package br.com.alura.screenmatch.domain.filme;

public record DadosPesquisaFilme(String nome, String duracao, String ano, Long idGenero) {

    public DadosCadastroFilme converteParaCadastro() {
        Integer anoInt = Integer.parseInt(ano);
        Integer duracaoInt = Integer.parseInt(duracao.replaceAll("\\D+",""));

        var dadosCadastro = new DadosCadastroFilme(this.nome, duracaoInt, anoInt, this.idGenero());

        return dadosCadastro;
    }
}
