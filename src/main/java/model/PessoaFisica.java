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
public class PessoaFisica extends Cliente {

    private String nome;
    private String cpf;

    public PessoaFisica(String nome, String cpf, String email, String endereco, String telefone, boolean ativo) {
        super(email, endereco, telefone, ativo);
        this.nome = nome;
        this.cpf = cpf;
    }

    public PessoaFisica(String nome, String cpf, int id, String email, String endereco, String telefone, boolean ativo) {
        super(id, email, endereco, telefone, ativo);
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
