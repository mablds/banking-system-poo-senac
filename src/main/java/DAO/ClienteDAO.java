/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ClienteDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.GerenciadorConexao;

/**
 *
 * @author Sakemi
 */
public class ClienteDAO {
    public static ClienteDTO cadastrar(ClienteDTO newCliente) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        ResultSet rs = null;
        
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("INSERT INTO clientes "
                    + "(registro, nome, telefone, email, endereco, tipo, ativo) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            instrucaoSQL.setString(1, newCliente.getRegistro());
            instrucaoSQL.setString(2, newCliente.getNome());
            instrucaoSQL.setString(3, newCliente.getTelefone());
            instrucaoSQL.setString(4, newCliente.getEmail());
            instrucaoSQL.setString(5, newCliente.getEndereco());
            instrucaoSQL.setInt(6, newCliente.getTipo());
            instrucaoSQL.setBoolean(7, newCliente.isAtivo());
                        
            instrucaoSQL.executeUpdate();

            rs = instrucaoSQL.getGeneratedKeys();
            rs.next();
            
            int clienteId = rs.getInt(1);
            
            newCliente.setId(clienteId);
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            newCliente = null;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return newCliente;
    }

    public static List<ClienteDTO> listarClientes() {
        List<ClienteDTO> clientes = new ArrayList<>();
        Connection conexao;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM clientes WHERE ativo = true;");
            ResultSet rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String registro = rs.getString("registro");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String endereco = rs.getString("endereco");
                int tipo = rs.getInt("tipo");
                boolean ativo = rs.getBoolean("ativo");

                clientes.add(new ClienteDTO(id, registro, nome, telefone, email, endereco, tipo, ativo));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return clientes;

    }

}
