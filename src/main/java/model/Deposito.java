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
public class Deposito extends Transacao{
    private int idConta;

    public Deposito(int idConta, int id, String date, double valor) {
        super(id, date, valor);
        this.idConta = idConta;
    }

    public Deposito(int idConta, String date, double valor) {
        super(date, valor);
        this.idConta = idConta;
    }
    
    
    
    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
