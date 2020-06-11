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
public class Transferencia extends Transacao{
    private int idPagador;
    private int idReceptor;

    public Transferencia(int idPagador, int idReceptor, int id, Date date, double valor) {
        super(id, date, valor);
        this.idPagador = idPagador;
        this.idReceptor = idReceptor;
    }

    public Transferencia(int idPagador, int idReceptor, Date date, double valor) {
        super(date, valor);
        this.idPagador = idPagador;
        this.idReceptor = idReceptor;
    }
    
    public int getIdPagador() {
        return idPagador;
    }

    public void setIdPagador(int idPagador) {
        this.idPagador = idPagador;
    }

    public int getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(int idReceptor) {
        this.idReceptor = idReceptor;
    }
    
    
}
