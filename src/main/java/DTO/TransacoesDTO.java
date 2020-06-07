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
public class TransacoesDTO {

    private final int id;
    private final String transactionDate;
    private final double valor;
    private final int pagoPor;
    private final int receptor;

    public TransacoesDTO(int id, String transactionDate, double valor, int pagoPor, int receptor) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.valor = valor;
        this.pagoPor = pagoPor;
        this.receptor = receptor;
    }

    public int getId() {
        return id;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public double getValor() {
        return valor;
    }

    public int getPagoPor() {
        return pagoPor;
    }

    public int getReceptor() {
        return receptor;
    }

}
