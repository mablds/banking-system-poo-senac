/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Sakemi
 */
public class ClienteDTO {

    private int id;
    private final String registro;
    private final String nome;
    private final String telefone;
    private final String email;
    private final String endereco;
    private final String usuario;
    private String password;
    private final int tipo;
    private boolean ativo;

    @JsonCreator
    public ClienteDTO(
            @JsonProperty("id") int id,
            @JsonProperty("registro") String registro,
            @JsonProperty("nome") String nome,
            @JsonProperty("telefone") String telefone,
            @JsonProperty("email") String email,
            @JsonProperty("endereco") String endereco,
            @JsonProperty("usuario") String usuario,
            @JsonProperty("password") String password,
            @JsonProperty("tipo") int tipo,
            @JsonProperty("ativo") boolean ativo
    ) {
        this.id = id;
        this.registro = registro;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
        this.ativo = ativo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAtivo(boolean ativo) {
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
