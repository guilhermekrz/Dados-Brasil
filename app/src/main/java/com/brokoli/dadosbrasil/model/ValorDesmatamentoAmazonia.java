package com.brokoli.dadosbrasil.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ValorDesmatamentoAmazonia extends RealmObject {
    public static final String ANO = "ano";

    private Integer valor;
    private Integer brasil_ibge;
    @PrimaryKey
    private Integer ano;
    private Boolean valor_referencia;

    public ValorDesmatamentoAmazonia() {

    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getBrasil_ibge() {
        return brasil_ibge;
    }

    public void setBrasil_ibge(Integer brasil_ibge) {
        this.brasil_ibge = brasil_ibge;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Boolean getValor_referencia() {
        return valor_referencia;
    }

    public void setValor_referencia(Boolean valor_referencia) {
        this.valor_referencia = valor_referencia;
    }
}
