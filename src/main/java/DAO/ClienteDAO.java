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

    public static ClienteDTO buscarCliente(int id) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        ClienteDTO clienteEncontrado = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM clientes WHERE id = ?;");
            instrucaoSQL.setInt(1, id);

            ResultSet rs = instrucaoSQL.executeQuery();
            
            while(rs.next()){
                int idCliente = rs.getInt("id");
                String registro = rs.getString("registro");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String endereco = rs.getString("endereco");
                int tipo = rs.getInt("tipo");
                boolean ativo = rs.getBoolean("ativo");
                
                clienteEncontrado = new ClienteDTO(idCliente, registro, nome, telefone, email, endereco, tipo, ativo);
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
        return clienteEncontrado;
    }
    
    public static ClienteDTO alterar(ClienteDTO updateCliente) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("UPDATE clientes "
                    + "SET registro = ?, nome = ?, telefone = ?, email = ?, endereco = ?, tipo = ?, ativo = ? "
                    + "WHERE id = ?;");

            instrucaoSQL.setString(1, updateCliente.getRegistro());
            instrucaoSQL.setString(2, updateCliente.getNome());
            instrucaoSQL.setString(3, updateCliente.getTelefone());
            instrucaoSQL.setString(4, updateCliente.getEmail());
            instrucaoSQL.setString(5, updateCliente.getEndereco());
            instrucaoSQL.setInt(6, updateCliente.getTipo());
            instrucaoSQL.setBoolean(7, updateCliente.isAtivo());
            instrucaoSQL.setInt(8, updateCliente.getId());
                        
            instrucaoSQL.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            updateCliente = null;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return updateCliente;
    }
    
    public static boolean deletar(ClienteDTO deleteCliente) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        boolean retorno = false;
        
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("UPDATE clientes "
                    + "SET ativo = false "
                    + "WHERE id = ?;");

            instrucaoSQL.setInt(1, deleteCliente.getId());
                        
            instrucaoSQL.executeUpdate();
            
            retorno = true;
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return retorno;
    }
    
    public static ClienteDTO ativar(ClienteDTO ativeCliente) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("UPDATE clientes "
                    + "SET ativo = true "
                    + "WHERE id = ?;");

            instrucaoSQL.setInt(1, ativeCliente.getId());
                        
            instrucaoSQL.executeUpdate();

            ativeCliente.setAtivo(true);
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            ativeCliente = null;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return ativeCliente;
    }
}
