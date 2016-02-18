/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.controladores;

import com.fpmislata.banco.business.domain.SucursalBancaria;
import com.fpmislata.banco.business.service.SucursalBancariaService;
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
 * @author alumno
 */
@Controller
public class SucursalBancariaController {

    @Autowired
    SucursalBancariaService sucursalBancariaService;

    @Autowired
    JsonTransformer jsonTransformer;
//get

    @RequestMapping(value = "/sucursalbancaria/{idsucursalbancaria}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idsucursalbancaria") int idsucursalbancaria) {
        try {
            SucursalBancaria sucursalBancaria = sucursalBancariaService.get(idsucursalbancaria);
            String jsonSalida = jsonTransformer.ObjectToJson(sucursalBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
//find

    @RequestMapping(value = "/sucursalbancaria", method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {

            List<SucursalBancaria> sucursalBancaria;

            if (httpServletRequest.getParameter("identidadbancaria") != null) {
                sucursalBancaria = sucursalBancariaService.getByEntidad(Integer.parseInt(httpServletRequest.getParameter("identidadbancaria")));
            } else {
                sucursalBancaria = sucursalBancariaService.findAll();
            }

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            String jsonSalida = jsonTransformer.ObjectToJson(sucursalBancaria);
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//insert
    @RequestMapping(value = {"/sucursalbancaria"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            SucursalBancaria sucursalBancaria = (SucursalBancaria) jsonTransformer.fromJsonToObject(jsonEntrada, SucursalBancaria.class);
            sucursalBancariaService.insert(sucursalBancaria);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonTransformer.ObjectToJson(sucursalBancaria));

        } catch (BusinessException ex) {
            List<BusinessMessage> bussinessMessage = ex.getBusinessMessages();
            String jsonSalida = jsonTransformer.ObjectToJson(bussinessMessage);
            //System.out.println(jsonSalida);

            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonSalida);
            } catch (IOException ex1) {
                Logger.getLogger(SucursalBancariaController.class.getName()).log(Level.SEVERE, "Error devolviendo Lista de Mensajes", ex1);
            }
        } catch (Exception ex1) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex1.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex2) {
                Logger.getLogger(SucursalBancariaController.class.getName()).log(Level.SEVERE, "Error devolviendo la traza", ex2);
            }
        }
    }
//Delete

    @RequestMapping(value = "/sucursalbancaria/{idsucursalbancaria}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idsucursalbancaria") int idsucursalbancaria) {
        try {
            boolean deleteSucursal;
            deleteSucursal = sucursalBancariaService.delete(idsucursalbancaria);

            if (deleteSucursal == true) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//update
    @RequestMapping(value = "/sucursalbancaria/{idsucursalbancaria}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idsucursalbancaria") int idsucursalbancaria) {

        try {
            SucursalBancaria sucursalBancaria = (SucursalBancaria) jsonTransformer.fromJsonToObject(jsonEntrada, SucursalBancaria.class);
            sucursalBancariaService.update(sucursalBancaria);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonTransformer.ObjectToJson(sucursalBancaria));

        } catch (BusinessException ex) {
            List<BusinessMessage> bussinessMessage = ex.getBusinessMessages();
            String jsonSalida = jsonTransformer.ObjectToJson(bussinessMessage);
            //System.out.println(jsonSalida);

            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            try {
                httpServletResponse.getWriter().println(jsonSalida);
            } catch (IOException ex1) {
                Logger.getLogger(SucursalBancariaController.class.getName()).log(Level.SEVERE, "Error devolviendo Lista de Mensajes", ex1);
            }
        } catch (Exception ex1) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex1.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex2) {
                Logger.getLogger(SucursalBancariaController.class.getName()).log(Level.SEVERE, "Error devolviendo la traza", ex2);
            }
        }
    }
}
