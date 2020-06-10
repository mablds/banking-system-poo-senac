/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.simplebankpoo;

import DAO.ContasDAO;
import DTO.ContasDTO;
import java.util.List;
import model.Conta;
import model.ContaJuridica;
import model.ContaPessoaFisica;
import model.ContaPoupanca;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sakemi
 */
@RestController
public class ContasController {

    @GetMapping("/contas")
    public List<ContasDTO> contas() {
        List contas = ContasDAO.listarContas();
        return contas;
    }

    @GetMapping("/conta/{id}")
    public Conta conta(@PathVariable int id) {
        Conta conta = ContasDAO.buscarConta(id);
        return conta;
    }

    @PostMapping("/conta/create/pf") // pessoa fisica
    public Conta createContaPf(@RequestBody ContaPessoaFisica newConta) {
        Conta clienteCreated = ContasDAO.cadastrar(newConta);
        return clienteCreated;
    }
    
    @PostMapping("/conta/create/pj") //pessoa juridica
    public Conta createContaPj(@RequestBody ContaJuridica newConta) {
        Conta clienteCreated = ContasDAO.cadastrar(newConta);
        return clienteCreated;
    }
    
    @PostMapping("/conta/create/pp") //poupanca
    public Conta createContaPp(@RequestBody ContaPoupanca newConta) {
        Conta clienteCreated = ContasDAO.cadastrar(newConta);
        return clienteCreated;
    }
    
    @PutMapping("/conta/update")
    public Conta updateConta(@RequestBody Conta updateConta) {
        Conta clienteUpdated = ContasDAO.alterar(updateConta);
        return clienteUpdated;
    }
    
    @PutMapping("/conta/delete") //Fizemos como PUT porque apenas iremos alterar a propriedade de ativo no banco para false.
    public boolean deleteConta(@RequestBody Conta deleteConta) {
        boolean clienteDeleted = ContasDAO.deletar(deleteConta);
        return clienteDeleted;
    }
    
    @PutMapping("/conta/ativar") //Fizemos como PUT porque apenas iremos alterar a propriedade de ativo no banco para true.
    public Conta ativarConta(@RequestBody Conta ativeConta) {
        Conta clienteActivated = ContasDAO.ativar(ativeConta);
        return clienteActivated;
    }
}
