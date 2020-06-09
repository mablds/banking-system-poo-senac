/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.simplebankpoo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcelo
 */
@RestController
public class HomeController {
    @GetMapping("/")
    public String homeInit() {
        return "Bem vindo ao Simple Bank feito por Arthur Sakemi e Marcelo Arthur.";
    }
}
