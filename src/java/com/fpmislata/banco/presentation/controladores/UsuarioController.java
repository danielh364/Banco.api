/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.controladores;

import com.fpmislata.banco.business.domain.Usuario;
import com.fpmislata.banco.business.service.UsuarioService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.core.BusinessMessage;
import com.fpmislata.banco.presentation.Json.JsonTransformer;
import com.fpmislata.banco.security.PasswordManager;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author danie
 */
@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    PasswordManager passwordManager;
//get

    @RequestMapping(value = "/usuario/{idUsuario}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idUsuario") int idUsuario) {
        try {
            Usuario usuario = usuarioService.get(idUsuario);
            String jsonSalida = jsonTransformer.ObjectToJson(usuario);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
//find

    @RequestMapping(value = "/usuario", method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {

            List<Usuario> usuarios;

            if (httpServletRequest.getParameter("name") != null) {

                usuarios = usuarioService.findByNombre(httpServletRequest.getParameter("name"));
            } else {
                usuarios = usuarioService.findAll();
            }

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            String jsonSalida = jsonTransformer.ObjectToJson(usuarios);
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//insert
    @RequestMapping(value = {"/usuario"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            Usuario usuario = (Usuario) jsonTransformer.fromJsonToObject(jsonEntrada, Usuario.class);
            if (usuario == null) {
                throw new BusinessException("Campo", "Debe rellenar los datos");
            }

            if (!passwordManager.checkComplexity(usuario.getPassword()) || usuario.getPassword().isEmpty()) {
                throw new BusinessException("Password", "La contraseña debe contener Minusculas, Mayúsculas, Dígitos y de 6-20 caractéres");
            }

            usuario.setPassword(passwordManager.encrypt(usuario.getPassword()));

            usuarioService.insert(usuario);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonTransformer.ObjectToJson(usuario));

        } catch (BusinessException ex) {
            List<BusinessMessage> bussinessMessage = ex.getBusinessMessages();
            String jsonSalida = jsonTransformer.ObjectToJson(bussinessMessage);
            //System.out.println(jsonSalida);

            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonSalida);
            } catch (IOException ex1) {
                Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, "Error devolviendo Lista de Mensajes", ex1);
            }
        } catch (Exception ex1) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex1.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex2) {
                Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, "Error devolviendo la traza", ex2);
            }
        }
    }
//Delete

    @RequestMapping(value = "/usuario/{idUsuario}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idUsuario") int idUsuario) {
        try {
            boolean deleteUsuario;
            deleteUsuario = usuarioService.delete(idUsuario);

            if (deleteUsuario == true) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//update
    @RequestMapping(value = "/usuario/{idUsuario}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idUsuario") int idUsuario) {

        try {
            Usuario usuario = (Usuario) jsonTransformer.fromJsonToObject(jsonEntrada, Usuario.class);
            usuarioService.update(usuario);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonTransformer.ObjectToJson(usuario));

        } catch (BusinessException ex) {
            List<BusinessMessage> bussinessMessage = ex.getBusinessMessages();
            String jsonSalida = jsonTransformer.ObjectToJson(bussinessMessage);
            //System.out.println(jsonSalida);

            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonSalida);
            } catch (IOException ex1) {
                Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, "Error devolviendo Lista de Mensajes", ex1);
            }
        } catch (Exception ex1) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex1.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex2) {
                Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, "Error devolviendo la traza", ex2);
            }
        }
    }

}
