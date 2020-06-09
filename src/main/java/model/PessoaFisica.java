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

    @JsonCreator
    public PessoaFisica(
            @JsonProperty("id") int id,
            @JsonProperty("registro") String cpf,
            @JsonProperty("nome") String nome,
            @JsonProperty("telefone") String telefone,
            @JsonProperty("email") String email,
            @JsonProperty("id") String endereco,
            @JsonProperty("id") int tipo, boolean ativo) {
        super(id, email, endereco, telefone, tipo, ativo);
        this.nome = nome;
        this.cpf = cpf;
    }

    @JsonCreator
    public PessoaFisica(
            @JsonProperty("registro") String cpf,
            @JsonProperty("nome") String nome,
            @JsonProperty("telefone") String telefone,
            @JsonProperty("email") String email,
            @JsonProperty("id") String endereco,
            @JsonProperty("id") int tipo, boolean ativo) {
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
