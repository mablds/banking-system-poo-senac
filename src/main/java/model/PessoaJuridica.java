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
public class PessoaJuridica extends Cliente {

    private String nomeFantasia;
    private String cnpj;

    public PessoaJuridica(String nomeFantasia, String cnpj, String email, String endereco, int telefone, boolean ativo) {
        super(email, endereco, telefone, ativo);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

    public PessoaJuridica(String nomeFantasia, String cnpj, int id, String email, String endereco, int telefone, boolean ativo) {
        super(id, email, endereco, telefone, ativo);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

}
