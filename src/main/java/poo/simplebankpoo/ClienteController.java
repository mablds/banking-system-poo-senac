/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.simplebankpoo;

import DAO.ClienteDAO;
import DTO.ClienteDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sakemi
 */
@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<ClienteDTO> cliente() {
        List clientes = ClienteDAO.listarClientes();

        return clientes;
    }

}
