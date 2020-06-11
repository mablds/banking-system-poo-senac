/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.simplebankpoo;

import dao.CredencialDAO;
import model.Credencial;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcelo
 */
@RestController
public class CredencialController {
    
    @PostMapping("/auth")
    public boolean checkCredentials(@RequestBody Credencial cred) {
        return CredencialDAO.checkLogin(cred);
    }
}
