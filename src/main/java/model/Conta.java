/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Marcelo
 */
public abstract class Conta {

    private int id;
    private int cliente;
    private String dataCriacao;
    private double saldo;
    private int tipo;
    private boolean ativa;
    
    public Conta(int id, int cliente, String dataCriacao, double saldo, int tipo, boolean ativa) {
        this.id = id;
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.saldo = saldo;
        this.tipo = tipo;
        this.ativa = ativa;
    }
    
    public Conta(int cliente, int tipo, boolean ativa) {
        this.cliente = cliente;
        this.saldo = 0.0;
        this.tipo = tipo;
        this.ativa = ativa;
    }

    public int getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

}
