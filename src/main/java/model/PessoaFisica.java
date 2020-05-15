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
public class PessoaFisica extends Cliente {

    private String nome;
    private Date nascimento;
    private String genero;
    private String cpf;
    private String rg;

    public PessoaFisica(String nome, Date nascimento, String genero, String cpf, String rg, int id, String email, String endereco, int telefone, boolean ativo) {
        super(id, email, endereco, telefone, ativo);
        this.nome = nome;
        this.nascimento = nascimento;
        this.genero = genero;
        this.cpf = cpf;
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

}
