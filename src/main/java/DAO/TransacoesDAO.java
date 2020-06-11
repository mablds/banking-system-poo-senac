/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DAO.ContasDAO;
import DTO.ContasDTO;
import DTO.DepositoDTO;
import DTO.SaqueDTO;
import DTO.TransferenciaDTO;
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
import model.Deposito;
import model.Saque;
import model.Transacao;
import model.Transferencia;
import utils.GerenciadorConexao;

/**
 *
 * @author Marcelo
 */
public class TransacoesDAO {
    public static double saque(SaqueDTO saque) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        PreparedStatement instrucaoSQL2 = null;
        PreparedStatement instrucaoSQL3 = null;
        double contaSaldo = 0.0;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            conexao.setAutoCommit(false);
            
            instrucaoSQL = conexao.prepareStatement("UPDATE contas " 
                    + "SET saldo = saldo - ? "
                    + "WHERE id = ?;");
            
            instrucaoSQL.setDouble(1, saque.getValor());
            instrucaoSQL.setInt(2, saque.getIdConta());

            instrucaoSQL.executeUpdate();

            instrucaoSQL2 = conexao.prepareStatement("SELECT saldo FROM contas "
                    + "WHERE id = ?;", 
                    Statement.RETURN_GENERATED_KEYS);

            instrucaoSQL2.setInt(1, saque.getIdConta());
                        
            ResultSet rs = instrucaoSQL2.executeQuery();
            rs.next();
            
            contaSaldo = rs.getDouble(1);
            
            instrucaoSQL3 = conexao.prepareStatement("INSERT INTO transacoes "
                + "(tr_date, valor, pago_por, receptor) "
                + "VALUES (?, ?, ?, ?); ");
            
            Date now = new Date();
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            instrucaoSQL3.setString(1, formatter.format(now));
            instrucaoSQL3.setDouble(2, saque.getValor());
            instrucaoSQL3.setInt(3, saque.getIdConta());
            instrucaoSQL3.setString(4, null);
            
            instrucaoSQL3.executeUpdate();
                    
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                
                if(instrucaoSQL2 != null) {
                    instrucaoSQL2.close();
                }
                
                if(instrucaoSQL3 != null) {
                    instrucaoSQL3.close();
                }
                
                conexao.setAutoCommit(true);
                
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return contaSaldo;
    }
    
    public static double deposito(DepositoDTO deposito) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        PreparedStatement instrucaoSQL2 = null;
        PreparedStatement instrucaoSQL3 = null;
        double contaSaldo = 0.0;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            conexao.setAutoCommit(false);
            
            instrucaoSQL = conexao.prepareStatement("UPDATE contas " 
                    + "SET saldo = saldo + ? "
                    + "WHERE id = ?;");
            
            instrucaoSQL.setDouble(1, deposito.getValor());
            instrucaoSQL.setInt(2, deposito.getIdConta());

            instrucaoSQL.executeUpdate();

            instrucaoSQL2 = conexao.prepareStatement("SELECT saldo FROM contas "
                    + "WHERE id = ?;", 
                    Statement.RETURN_GENERATED_KEYS);

            instrucaoSQL2.setInt(1, deposito.getIdConta());
                        
            ResultSet rs = instrucaoSQL2.executeQuery();
            rs.next();
            
            contaSaldo = rs.getDouble(1);
            
            instrucaoSQL3 = conexao.prepareStatement("INSERT INTO transacoes "
                + "(tr_date, valor, pago_por, receptor) "
                + "VALUES (?, ?, ?, ?); ");
            
            Date now = new Date();
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            instrucaoSQL3.setString(1, formatter.format(now));
            instrucaoSQL3.setDouble(2, deposito.getValor());
            instrucaoSQL3.setString(3, null);
            instrucaoSQL3.setInt(4, deposito.getIdConta());
            
            instrucaoSQL3.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                
                if(instrucaoSQL2 != null) {
                    instrucaoSQL2.close();
                }
                
                if(instrucaoSQL3 != null) {
                    instrucaoSQL3.close();
                }
                
                conexao.setAutoCommit(true);
                
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return contaSaldo;
    }
    
    public static boolean transferencia(TransferenciaDTO transferencia) {
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
        PreparedStatement instrucaoSQL2 = null;
        PreparedStatement instrucaoSQL3 = null;
        boolean transferenciaOk = false;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            conexao.setAutoCommit(false);
            
            instrucaoSQL = conexao.prepareStatement("UPDATE contas " 
                    + "SET saldo = saldo - ? "
                    + "WHERE id = ?;");
            
            instrucaoSQL.setDouble(1, transferencia.getValor());
            instrucaoSQL.setInt(2, transferencia.getIdPagador());

            instrucaoSQL.executeUpdate();

            instrucaoSQL2 = conexao.prepareStatement("UPDATE contas " 
                    + "SET saldo = saldo + ? "
                    + "WHERE id = ?;");
            
            instrucaoSQL2.setDouble(1, transferencia.getValor());
            instrucaoSQL2.setInt(2, transferencia.getIdReceptor());
            
            instrucaoSQL2.executeUpdate();
            
            instrucaoSQL3 = conexao.prepareStatement("INSERT INTO transacoes "
                + "(tr_date, valor, pago_por, receptor) "
                + "VALUES (?, ?, ?, ?); ");
            
            Date now = new Date();
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            instrucaoSQL3.setString(1, formatter.format(now));
            instrucaoSQL3.setDouble(2, transferencia.getValor());
            instrucaoSQL3.setInt(3, transferencia.getIdPagador());
            instrucaoSQL3.setInt(4, transferencia.getIdReceptor());
            
            instrucaoSQL3.executeUpdate();
            
            transferenciaOk = true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                
                if(instrucaoSQL2 != null) {
                    instrucaoSQL2.close();
                }
                
                if(instrucaoSQL3 != null) {
                    instrucaoSQL3.close();
                }
                
                conexao.setAutoCommit(true);
                
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return transferenciaOk;
    }

    public static List<Transacao> listarTransacoes(int id){
        List<Transacao> relatorio = new ArrayList<>();
        Connection conexao;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM transacoes " 
                    + "WHERE pago_por = ? OR receptor = ?");
            
            instrucaoSQL.setInt(1, id);
            instrucaoSQL.setInt(2, id);
            
            ResultSet rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                int idTransacao = rs.getInt("id");
                String trDate = rs.getString("tr_date");
                double valor = rs.getDouble("valor");
                String pagoPor = rs.getString("pago_por");
                String receptor = rs.getString("receptor");

                if(pagoPor != null && receptor != null) { //Transferencia
                    //int idPagador, int idReceptor, int id, String date, double valor
                    relatorio.add(new Transferencia(Integer.parseInt(pagoPor), Integer.parseInt(receptor), idTransacao, trDate, valor));
                } else if(pagoPor != null) { //Saque
                    //int idConta, int id, String date, double valor
                    relatorio.add(new Saque(Integer.parseInt(pagoPor), idTransacao, trDate, valor * -1));
                } else { //Deposito
                    //int idConta, int id, String date, double valor
                    relatorio.add(new Deposito(Integer.parseInt(receptor), idTransacao, trDate, valor));
                }
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return relatorio;
    }
    
    public static List<Transacao> listarTodasTransacoes() {
        List<Transacao> relatorio = new ArrayList<>();
        Connection conexao;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM transacoes;");
            
            ResultSet rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                int idTransacao = rs.getInt("id");
                String trDate = rs.getString("tr_date");
                double valor = rs.getDouble("valor");
                String pagoPor = rs.getString("pago_por");
                String receptor = rs.getString("receptor");

                if(pagoPor != null && receptor != null) { //Transferencia
                    //int idPagador, int idReceptor, int id, String date, double valor
                    relatorio.add(new Transferencia(Integer.parseInt(pagoPor), Integer.parseInt(receptor), idTransacao, trDate, valor));
                } else if(pagoPor != null) { //Saque
                    //int idConta, int id, String date, double valor
                    relatorio.add(new Saque(Integer.parseInt(pagoPor), idTransacao, trDate, valor * -1));
                } else { //Deposito
                    //int idConta, int id, String date, double valor
                    relatorio.add(new Deposito(Integer.parseInt(receptor), idTransacao, trDate, valor));
                }
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (instrucaoSQL != null) {
                    instrucaoSQL.close();
                }
                GerenciadorConexao.fecharConexao();
            } catch (SQLException ex) {
            }
        }
        return relatorio;
    }
}
