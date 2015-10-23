<%-- 
    Document   : findall.json
    Created on : 01-oct-2015, 16:58:15
    Author     : danie
--%>


<%@page import="com.fpmislata.banco.presentation.Json.JsonTransformer"%>
<%@page import="com.fpmislata.banco.presentation.Json.Impl.JsonTransformerImplJackson"%>
<%@page import="com.fpmislata.banco.business.service.impl.EntidadBancariaServiceImpl"%>
<%@page contentType="application/json" pageEncoding="UTF-8" %>
<%@page import="com.fpmislata.banco.business.domain.EntidadBancaria"%>
<%@page import="com.fpmislata.banco.business.service.EntidadBancariaService"%>
<%@page import="java.util.List"%>

<%
    
    EntidadBancariaService entidadBancariaService = new EntidadBancariaServiceImpl();
    List<EntidadBancaria> entidadBancarias = entidadBancariaService.findAll();

    JsonTransformer jsonTransformerEntidadBancaria = new JsonTransformerImplJackson();
    out.println(jsonTransformerEntidadBancaria.ObjectToJson(entidadBancarias));


%>


