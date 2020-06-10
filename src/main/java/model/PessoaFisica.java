/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Marcelo
 */
public class PessoaFisica extends Cliente {

    private String nome;
    private String cpf;

    public PessoaFisica(int id, String cpf, String nome, String telefone, String email, String endereco, int tipo, boolean ativo) {
        super(id, email, endereco, telefone, tipo, ativo);
        this.nome = nome;
        this.cpf = cpf;
    }

    public PessoaFisica(String cpf, String nome, String telefone, String email, String endereco, int tipo, boolean ativo) {
        super(email, endereco, telefone, tipo, ativo);
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
