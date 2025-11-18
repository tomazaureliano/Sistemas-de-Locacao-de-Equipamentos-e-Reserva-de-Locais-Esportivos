package model;

public class Funcionario {
    private String nome;
    private String cpf;
    public enum Autorizacao {
        AUTORIZADO,
        NAOAUTORIZADO
    }
    private Autorizacao autorizacao;
    public Funcionario(String nome, String cpf, Autorizacao autorizacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.autorizacao = Autorizacao.AUTORIZADO;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Autorizacao getAutorizacao() {
        return autorizacao;
    }
    public void setAutorizacao(Autorizacao autorizacao) {
        this.autorizacao = autorizacao;
    }

}
