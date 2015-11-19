/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.controladores;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.security.PasswordManager;
import com.fpmislata.banco.presentation.security.WebSession;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author alumno
 */
@Controller
public class LoginController {

    @Autowired
    PasswordManager passwordManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            Usuario usuario = new Usuario("Daniel", passwordManager.encrypt("12345"));
            HttpSession httpSession = httpServletRequest.getSession();
            WebSession webSession = new WebSession(usuario, new Date());
            httpSession.setAttribute("webSession", webSession);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
