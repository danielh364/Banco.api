<%-- 
    Document   : findByName.json
    Created on : 01-oct-2015, 16:58:34
    Author     : danie
--%>


<%@page import="com.fpmislata.banco.presentation.Json.JsonTransformer"%>
<%@page import="com.fpmislata.banco.presentation.Json.Impl.JsonTransformerImplJackson"%>
<%@page import="com.fpmislata.banco.business.service.impl.EntidadBancariaServiceImpl"%>
<%@page contentType="application/json" pageEncoding="UTF-8" %>
<%@page import="com.fpmislata.banco.business.service.EntidadBancariaService"%>
<%@page import="java.util.List"%>
<%@page import="com.fpmislata.banco.business.domain.EntidadBancaria"%>
<%
    EntidadBancariaService entidadBancariaService = new EntidadBancariaServiceImpl();
    String nombre = request.getParameter("nombre");
    List<EntidadBancaria> entidadesBancarias = entidadBancariaService.findByNombre(nombre);

    JsonTransformer jsonTransformerEntidadBancaria = new JsonTransformerImplJackson();
    out.println(jsonTransformerEntidadBancaria.ObjectToJson(entidadesBancarias));
%>
