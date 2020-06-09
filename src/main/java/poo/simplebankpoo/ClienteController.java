/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.simplebankpoo;

import DAO.ClienteDAO;
import DTO.ClienteDTO;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sakemi
 */
@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<ClienteDTO> clientes() {
        List clientes = ClienteDAO.listarClientes();
        return clientes;
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
}
