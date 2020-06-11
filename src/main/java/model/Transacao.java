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
public abstract class Transacao {
    private int id;
    private String date;
    private double valor;

    public Transacao(int id, String date, double valor) {
        this.id = id;
        this.date = date;
        this.valor = valor;
    }

    public Transacao(String date, double valor) {
        this.date = date;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
