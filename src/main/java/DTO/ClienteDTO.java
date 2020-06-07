/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Sakemi
 */
public class ClienteDTO {

    private final int id;
    private final String registro;
    private final String nome;
    private final String telefone;
    private final String email;
    private final String endereco;
    private final int tipo;
    private final boolean ativo;

    public ClienteDTO(int id, String registro, String nome, String telefone, String email, String endereco, int tipo, boolean ativo) {
        this.id = id;
        this.registro = registro;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.tipo = tipo;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public String getRegistro() {
        return registro;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getTipo() {
        return tipo;
    }

    public boolean isAtivo() {
        return ativo;
    }

}
