/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.ContasDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conta;
import model.ContaJuridica;
import model.ContaPessoaFisica;
import model.ContaPoupanca;
import utils.GerenciadorConexao;

/**
 *
 * @author Sakemi
 */
public class ContasDAO {
    public static Conta cadastrar(Conta newConta) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        ResultSet rs = null;
        Date now = new Date();
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("INSERT INTO contas "
                    + "(id_cliente, criacao, saldo, tipo, ativo) "
                    + "VALUES(?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            instrucaoSQL.setInt(1, newConta.getCliente());
            instrucaoSQL.setString(2, formatter.format(now));
            instrucaoSQL.setDouble(3, newConta.getSaldo());
            instrucaoSQL.setInt(4, newConta.getTipo());
            instrucaoSQL.setBoolean(5, newConta.isAtiva());
                        
            instrucaoSQL.executeUpdate();

            rs = instrucaoSQL.getGeneratedKeys();
            rs.next();
            
            int contaId = rs.getInt(1);
            
            newConta.setId(contaId);
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            newConta = null;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return newConta;
    }
    
    public static List<ContasDTO> listarContas() {
        List<ContasDTO> contas = new ArrayList<>();
        Connection conexao;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT contas.id , clientes.nome AS nome_cliente, clientes.registro, contas.criacao, contas.saldo, contas.tipo, contas.ativo FROM contas "
                    + "INNER JOIN clientes ON contas.id_cliente = clientes.id "
                    + "WHERE contas.ativo = true;");
            ResultSet rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeCliente = rs.getString("nome_cliente");
                String registro = rs.getString("registro");
                String criacao = rs.getString("criacao");
                double saldo = rs.getDouble("saldo");
                int tipoConta = rs.getInt("tipo");
                boolean ativo = rs.getBoolean("ativo");

                contas.add(new ContasDTO(id, nomeCliente, registro, criacao, saldo, tipoConta, ativo));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ContasDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        return contas;

    }
    
    public static Conta buscarConta(int id) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        Conta contaEncontrada = null;
        
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM contas WHERE id = ?;");
            instrucaoSQL.setInt(1, id);

            ResultSet rs = instrucaoSQL.executeQuery();
            
            while(rs.next()){
                int idConta = rs.getInt("id"); //id_cliente, criacao, saldo, tipo, ativo
                int idClienteConta = rs.getInt("id_cliente");
                String criacao = rs.getString("criacao");
                double saldo = rs.getDouble("saldo");
                int tipo = rs.getInt("tipo");
                boolean ativo = rs.getBoolean("ativo");

                switch(tipo) {
                    case 1:
                        contaEncontrada = new ContaPessoaFisica(idConta, idClienteConta, criacao, tipo, saldo, ativo);
                        break;
                    case 2:
                        contaEncontrada = new ContaJuridica(idConta, idClienteConta, criacao, tipo, saldo, ativo);
                        break;
                    case 3:
                        contaEncontrada = new ContaPoupanca(idConta, idClienteConta, criacao, tipo, saldo, ativo);
                        break;
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
        return contaEncontrada;
    }
    
    public static Conta alterar(Conta updateConta) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("UPDATE contas "
                    + "SET id_cliente = ?, criacao = ?, saldo = ?, tipo = ?, ativo = ? "
                    + "WHERE id = ?;",
                    Statement.RETURN_GENERATED_KEYS);
            
            instrucaoSQL.setInt(1, updateConta.getCliente());
            instrucaoSQL.setString(2, updateConta.getDataCriacao());
            instrucaoSQL.setDouble(3, updateConta.getSaldo());
            instrucaoSQL.setInt(4, updateConta.getTipo());
            instrucaoSQL.setBoolean(5, updateConta.isAtiva());
            instrucaoSQL.setInt(6, updateConta.getId());
                        
            instrucaoSQL.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            updateConta = null;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return updateConta;
    } 
    
    public static boolean deletar(int id) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        boolean contaDeletada;
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("UPDATE contas "
                    + "SET ativo = false "
                    + "WHERE id = ?;");
            
            instrucaoSQL.setInt(1, id);
                        
            instrucaoSQL.executeUpdate();
            contaDeletada = true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            contaDeletada = false;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return contaDeletada;
    }
    
    public static boolean ativar(int id) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        boolean contaAtivada;
        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("UPDATE contas "
                    + "SET ativo = true "
                    + "WHERE id = ?;");
            
            instrucaoSQL.setInt(1, id);
                        
            instrucaoSQL.executeUpdate();
            contaAtivada = true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            contaAtivada = false;
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return contaAtivada;
    }
}
