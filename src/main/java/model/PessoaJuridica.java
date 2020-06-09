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

    public PessoaJuridica(int id, String cnpj, String nomeFantasia, String telefone, String email, String endereco, int tipo, boolean ativo) {
        super(id, email, endereco, telefone, tipo, ativo);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

    public PessoaJuridica(String cnpj, String nomeFantasia, String telefone, String email, String endereco, int tipo, boolean ativo) {
        super(email, endereco, telefone, tipo, ativo);
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
