/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.simplebankpoo;

import DTO.DepositoDTO;
import DTO.SaqueDTO;
import DTO.TransferenciaDTO;
import dao.TransacoesDAO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcelo
 */
@CrossOrigin
@RestController
public class TransacoesController {
    
    @PostMapping("/transacoes/saque")
    public double saque(@RequestBody SaqueDTO saque) {
        return TransacoesDAO.saque(saque);
    }
    
    @PostMapping("/transacoes/deposito")
    public double deposito(@RequestBody DepositoDTO deposito) {
        return TransacoesDAO.deposito(deposito);
    }
    
    @PutMapping("/transacoes/transferencia")
    public boolean transferencia(@RequestBody TransferenciaDTO transferencia) {
        return TransacoesDAO.transferencia(transferencia);
    }
}
