/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Sakemi
 */
public class ContasDTO {

    private final int id;
    private final String nomeCliente;
    private final String registro;
    private final String criacao;
    private final double saldo;
    private final int tipoConta;
    private final boolean ativo;

    public ContasDTO(int id, String nomeCliente, String registro, String criacao, double saldo, int tipoConta, boolean ativo) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.registro = registro;
        this.criacao = criacao;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getRegistro() {
        return registro;
    }

    public String getCriacao() {
        return criacao;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public boolean isAtivo() {
        return ativo;
    }

}
