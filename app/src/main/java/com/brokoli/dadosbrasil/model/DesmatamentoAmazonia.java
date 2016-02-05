package com.brokoli.dadosbrasil.model;

import java.util.List;

public class DesmatamentoAmazonia {
    private long id;
    private String nome;
    private List<ValorDesmatamentoAmazonia> valores;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ValorDesmatamentoAmazonia> getValores() {
        return valores;
    }

    public void setValores(List<ValorDesmatamentoAmazonia> valores) {
        this.valores = valores;
    }
}
