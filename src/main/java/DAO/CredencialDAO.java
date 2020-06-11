/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Credencial;
import utils.GerenciadorConexao;


/**
 *
 * @author Marcelo
 */
public class CredencialDAO {
    public static Credencial buscarCredencial(String usuario) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        Credencial credEncontrada = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM credenciais WHERE usuario = ?;");
            instrucaoSQL.setString(1, usuario);

            ResultSet rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                int idCred = rs.getInt("id");
                int idCliente = rs.getInt("id_cliente");
                String usuarioDb = rs.getString("usuario");
                String senha = rs.getString("senha");
                
                credEncontrada = new Credencial(idCred, idCliente, usuarioDb, senha);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CredencialDAO.class.getName()).log(Level.SEVERE, null, ex);
            credEncontrada = null;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            } finally {
                try {
                    if (instrucaoSQL != null) {
                        instrucaoSQL.close();
                    }
                    GerenciadorConexao.fecharConexao();
                } catch (SQLException ex) {
                }
            }
        }
        return credEncontrada;
    }
    
    public static boolean checkLogin(Credencial cred) {
        Credencial usuarioBuscado = buscarCredencial(cred.getUsuario());
        boolean credenciaisCorretas = false;
        
        if(usuarioBuscado != null && cred != null) {
            System.out.println(usuarioBuscado.getPassword());
            System.out.println(cred.getPassword());
            if(usuarioBuscado.getPassword().equals(cred.getPassword())){
                credenciaisCorretas = true;
            }
        }
        
        return credenciaisCorretas;
    }
}
