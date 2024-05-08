package com.example.jokenpo;

public class ItemLista {
    private String nome;
    private int jogada;
    private String resultado;

    public ItemLista(String nome, int jogada, String resultado) {
        this.nome = nome;
        this.jogada = jogada;
        this.resultado = resultado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getJogada() {
        return jogada;
    }

    public void setJogada(int jogada) {
        this.jogada = jogada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
