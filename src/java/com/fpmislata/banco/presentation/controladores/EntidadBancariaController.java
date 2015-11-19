/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.controladores;

import com.fpmislata.banco.business.domain.EntidadBancaria;
import com.fpmislata.banco.business.service.EntidadBancariaService;
import com.fpmislata.banco.presentation.Json.JsonTransformer;
import java.util.List;
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
public class EntidadBancariaController {

    @Autowired
    EntidadBancariaService entidadBancariaService;

    @Autowired
    JsonTransformer jsonTransformer;
//get

    @RequestMapping(value = "/entidadbancaria/{identidadbancaria}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("identidadbancaria") int identidadbancaria) {
        try {
            EntidadBancaria entidadBancaria = entidadBancariaService.get(identidadbancaria);
            String jsonSalida = jsonTransformer.ObjectToJson(entidadBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
//find

    @RequestMapping(value = "/entidadbancaria", method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {

            List<EntidadBancaria> entidadBancarias;

            if (httpServletRequest.getParameter("nombre") != null) {

                entidadBancarias = entidadBancariaService.findByNombre(httpServletRequest.getParameter("nombre"));
            } else {
                entidadBancarias = entidadBancariaService.findAll();
            }

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            String jsonSalida = jsonTransformer.ObjectToJson(entidadBancarias);
            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//insert
    @RequestMapping(value = {"/entidadbancaria"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            EntidadBancaria entidadBancaria = (EntidadBancaria) jsonTransformer.fromJsonToObject(jsonEntrada, EntidadBancaria.class);
            entidadBancariaService.insert(entidadBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            String jsonSalida = jsonTransformer.ObjectToJson(entidadBancaria);

            httpServletResponse.getWriter().println(jsonSalida);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
//Delete

    @RequestMapping(value = "/entidadbancaria/{identidadbancaria}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("identidadbancaria") int identidadbancaria) {
        try {
            boolean deleteSeguro;
            deleteSeguro = entidadBancariaService.delete(identidadbancaria);

            if (deleteSeguro == true) {
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//update
    @RequestMapping(value = "/entidadbancaria/{identidadBancaria}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("identidadBancaria") int identidadBancaria) {
        try {
            EntidadBancaria entidadBancaria = (EntidadBancaria) jsonTransformer.fromJsonToObject(jsonEntrada, EntidadBancaria.class);
            entidadBancariaService.update(entidadBancaria);
            String jsonSalida = jsonTransformer.ObjectToJson(entidadBancaria);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
