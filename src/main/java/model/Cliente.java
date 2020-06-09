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
public abstract class Cliente {

    private int id;
    private String email;
    private String endereco;
    private String telefone;
    private int tipo;
    private boolean ativo;

    public Cliente(int id, String email, String endereco, String telefone, int tipo, boolean ativo) {
        this.id = id;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipo = tipo;
        this.ativo = ativo;
    }

    public Cliente(String email, String endereco, String telefone, int tipo, boolean ativo) {
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.tipo = tipo;
        this.ativo = ativo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
