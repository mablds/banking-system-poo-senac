/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Marcelo
 */
public class Credencial {
    private int id;
    private int idCliente;
    private String usuario;
    private String password;

    @JsonCreator
    public Credencial(
        @JsonProperty("usuario") String usuario,
        @JsonProperty("password") String password
    ) {
        this.usuario = usuario;
        this.password = password;
    }
    
    public Credencial(int id, int idCliente, String usuario, String password) {
        this.id = id;
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.password = password;
    }

    public Credencial(int idCliente, String usuario, String password) {
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String encodeSenha(String senha) {
        return BCrypt.withDefaults().hashToString(12, senha.toCharArray());
    }
    
    public boolean validarSenha(String senha) {
        BCrypt.Result result = BCrypt.verifyer().verify(senha.toCharArray(), this.getPassword());
        return result.verified;
    }
    
}
