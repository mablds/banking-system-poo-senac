/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.simplebankpoo;

import DAO.ClienteDAO;
import DTO.ClienteDTO;
import java.util.List;
import model.Cliente;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> clientes() {
        List clientes = ClienteDAO.listarClientes();
        return clientes;
    }

    @GetMapping("/cliente/{id}")
    public Cliente cliente(@PathVariable int id) {
        Cliente cliente = ClienteDAO.buscarCliente(id);
        return cliente;
    }

    @PostMapping("/cliente/create")
    public ClienteDTO createCliente(@RequestBody ClienteDTO newCliente) {
        ClienteDTO clienteCreated = ClienteDAO.cadastrar(newCliente);
        return clienteCreated;
    }

    @PutMapping("/cliente/update")
    public ClienteDTO updateCliente(@RequestBody ClienteDTO updateCliente) {
        ClienteDTO clienteUpdated = ClienteDAO.alterar(updateCliente);
        return clienteUpdated;
    }

    @PutMapping("/cliente/delete/{id}") //Fizemos como PUT porque apenas iremos alterar a propriedade de ativo no banco para false.
    public boolean deleteCliente(@PathVariable int id) {
        boolean clienteDeleted = ClienteDAO.deletar(id);
        return clienteDeleted;
    }

    @PutMapping("/cliente/ativar/{id}") //Fizemos como PUT porque apenas iremos alterar a propriedade de ativo no banco para true.
    public boolean ativarCliente(@PathVariable int id) {
        return ClienteDAO.ativar(id);
    }
}
