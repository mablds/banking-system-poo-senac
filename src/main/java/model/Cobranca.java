/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Marcelo
 */
public class Cobranca {

    private int id;
    private Date dataCriacao;
    private Date dataVencimento;
    private double valor;
    private Conta receptor;

    public Cobranca(int id, Date dataCriacao, Date dataVencimento, double valor, Conta receptor) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.receptor = receptor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Conta getReceptor() {
        return receptor;
    }

    public void setReceptor(Conta receptor) {
        this.receptor = receptor;
    }

}
