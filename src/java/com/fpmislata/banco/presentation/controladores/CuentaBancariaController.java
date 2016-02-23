/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.controladores;

import com.fpmislata.banco.business.domain.CuentaBancaria;
import com.fpmislata.banco.business.service.CuentaBancariaService;
import com.fpmislata.banco.core.BusinessException;
import com.fpmislata.banco.core.BusinessMessage;
import com.fpmislata.banco.presentation.Json.JsonTransformer;
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
public class CuentaBancariaController {

    @Autowired
    CuentaBancariaService cuentaBancariaService;

    @Autowired
    JsonTransformer jsonTransformer;

    //get
    @RequestMapping(value = {"/cuentabancaria/{idCuentaBancaria}"}, method = RequestMethod.GET, produces = "application/json")
    public void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idCuentaBancaria") int idCuentaBancaria) {
        try {
            CuentaBancaria cuentaBancaria = cuentaBancariaService.get(idCuentaBancaria);

            String jsonSalida = jsonTransformer.ObjectToJson(cuentaBancaria);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //insert
    @RequestMapping(value = "/cuentabancaria", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        try {
            CuentaBancaria cuentaBancaria = (CuentaBancaria) jsonTransformer.fromJsonToObject(jsonEntrada, CuentaBancaria.class);
            cuentaBancariaService.insert(cuentaBancaria);
            String jsonSalida = jsonTransformer.ObjectToJson(cuentaBancaria);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (BusinessException ex) {
            List<BusinessMessage> bussinessMessage = ex.getBusinessMessages();
            String jsonSalida = jsonTransformer.ObjectToJson(bussinessMessage);
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }
    }

    //find
    @RequestMapping(value = "/cuentabancaria", method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            List<CuentaBancaria> CuentasBancarias = cuentaBancariaService.findAll();
            String jsonSalida = jsonTransformer.ObjectToJson(CuentasBancarias);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }

    }

    //update   
    @RequestMapping(value = "/cuentabancaria/{idCuentaBancaria}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idCuentaBancaria") int idCuentaBancaria) {
        try {
            CuentaBancaria cuentaBancaria = (CuentaBancaria) jsonTransformer.fromJsonToObject(jsonEntrada, CuentaBancaria.class);
            cuentaBancariaService.update(cuentaBancaria);
            String jsonSalida = jsonTransformer.ObjectToJson(cuentaBancaria);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (BusinessException ex) {
            List<BusinessMessage> bussinessMessage = ex.getBusinessMessages();
            String jsonSalida = jsonTransformer.ObjectToJson(bussinessMessage);

            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonSalida);
            } catch (IOException ex1) {
                Logger.getLogger(CuentaBancariaService.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex1) {
                Logger.getLogger(CuentaBancariaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

//delete
    @RequestMapping(value = "/cuentabancaria/{idCuentaBancaria}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idCuentaBancaria") int idCuentaBancaria) {
        try {
            boolean comprobar;
            comprobar = cuentaBancariaService.delete(idCuentaBancaria);
            if (comprobar == true) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
