/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.simplebankpoo;

import DAO.ContasDAO;
import DTO.ClienteDTO;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sakemi
 */
@RestController
public class ContasController {

    @GetMapping("/contas")
    public List<ClienteDTO> contas() {
        List contas = ContasDAO.listarContas();
        return contas;
    }

}
