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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.GerenciadorConexao;

/**
 *
 * @author Sakemi
 */
public class ContasDAO {

    public static List<ContasDTO> listarContas() {
        List<ContasDTO> contas = new ArrayList<>();
        Connection conexao;
        PreparedStatement instrucaoSQL = null;

        try {
            conexao = GerenciadorConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT contas.id , clientes.nome AS nome_cliente, clientes.registro, contas.criacao, contas.saldo, contas.tipo, contas.ativo FROM contas "
                    + "INNER JOIN clientes ON contas.id_cliente = clientes.id;");
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

}
