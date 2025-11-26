package model;


import java.util.List;

public class LocalEsportivo {
    private String  nome;
    private String tipo;
    private int capacidade;
    private double valor;
    public enum Condicao {
        DISPONIVEL,
        RESERVADO,
        MANUTENCAO,
        EMUSO
    }
    private Condicao condicao;
    private List<Equipamento> equipamentos;
    public LocalEsportivo(String nome, String tipo, int capacidade, double valor, Condicao condicao,  List<Equipamento> equipamentos) {
        this.nome = nome;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.valor = valor;
        this.condicao = condicao;
        this.equipamentos = equipamentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Condicao getCondicao() {
        return condicao;
    }

    public void setCondicao(Condicao condicao) {
        this.condicao = condicao;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
}
