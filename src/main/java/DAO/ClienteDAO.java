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
import model.Cliente;
import model.Credencial;
import model.PessoaFisica;
import model.PessoaJuridica;
import utils.GerenciadorConexao;

/**
 *
 * @author Sakemi
 */
public class ClienteDAO {

    public static ClienteDTO cadastrar(ClienteDTO newCliente) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        PreparedStatement instrucaoSQL2 = null;
        ResultSet rs = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            conexao.setAutoCommit(false);
            
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
            
            Credencial newCred = new Credencial(clienteId, newCliente.getUsuario(), newCliente.getPassword());
            
            instrucaoSQL2 = conexao.prepareStatement("INSERT INTO credenciais "
                    + "(id_cliente, usuario, senha) "
                    + "VALUES(?, ?, ?);");
            
            instrucaoSQL2.setInt(1, newCred.getIdCliente());
            instrucaoSQL2.setString(2, newCred.getUsuario());
            instrucaoSQL2.setString(3, newCred.encodeSenha(newCred.getPassword()));
            System.out.println(newCred.encodeSenha(newCred.getPassword()));
            instrucaoSQL2.executeUpdate();
            
            newCliente.setPassword("******");
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            newCliente = null;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                
                if(instrucaoSQL2 != null) {
                    instrucaoSQL2.close();
                }
                
                conexao.setAutoCommit(true);
                
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return newCliente;
    }

    public static List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
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

                if (tipo == 1) {
                    clientes.add(new PessoaFisica(id, registro, nome, telefone, email, endereco, tipo, ativo));
                } else {
                    clientes.add(new PessoaJuridica(id, registro, nome, telefone, email, endereco, tipo, ativo));
                }

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

    public static Cliente buscarCliente(int id) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        Cliente clienteEncontrado = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM clientes WHERE id = ?;");
            instrucaoSQL.setInt(1, id);

            ResultSet rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("id");
                String registro = rs.getString("registro");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String endereco = rs.getString("endereco");
                int tipo = rs.getInt("tipo");
                boolean ativo = rs.getBoolean("ativo");

                if (tipo == 1) {
                    clienteEncontrado = new PessoaFisica(idCliente, registro, nome, telefone, email, endereco, tipo, ativo);
                } else {
                    clienteEncontrado = new PessoaJuridica(idCliente, registro, nome, telefone, email, endereco, tipo, ativo);
                }

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

    public static boolean deletar(int id) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        boolean retorno = false;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("UPDATE clientes "
                    + "SET ativo = false "
                    + "WHERE id = ?;");

            instrucaoSQL.setInt(1, id);

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

    public static boolean ativar(int id) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        boolean contaAtiva = false;
        
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("UPDATE clientes "
                    + "SET ativo = true "
                    + "WHERE id = ?;");

            instrucaoSQL.setInt(1, id);

            instrucaoSQL.executeUpdate();

            contaAtiva = true;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            contaAtiva = false;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return contaAtiva;
    }
}
