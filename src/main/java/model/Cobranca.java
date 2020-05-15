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
}
