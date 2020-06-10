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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Credencial;
import utils.GerenciadorConexao;

/**
 *
 * @author Marcelo
 */
public class CredencialDAO {
    public static boolean cadastrar(Credencial cred) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        boolean credencialCriada;
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("INSERT INTO credenciais "
                    + "(id_cliente, usuario, senha) "
                    + "VALUES(?, ?, ?);");

            instrucaoSQL.setInt(1, cred.getIdCliente());
            instrucaoSQL.setString(2, cred.getUsuario());
            instrucaoSQL.setString(3, cred.getPassword());
                        
            instrucaoSQL.executeUpdate();
            
            credencialCriada = true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            credencialCriada = false;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return credencialCriada;
    }
}
