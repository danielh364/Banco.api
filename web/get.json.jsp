<%-- 
    Document   : get.json
    Created on : 01-oct-2015, 13:23:39
    Author     : alumno
--%>


<%@page import="com.fpmislata.banco.presentation.Json.Impl.JsonTransformerImplJackson"%>
<%@page import="com.fpmislata.banco.presentation.Json.JsonTransformer"%>
<%@page import="com.fpmislata.banco.presentation.Json.JsonTransformer"%>
<%@page import="com.fpmislata.banco.business.service.impl.EntidadBancariaServiceImpl"%>
<%@page contentType="application/json" pageEncoding="UTF-8" %>
<%@page import="com.fpmislata.banco.business.service.EntidadBancariaService"%>
<%@page import="com.fpmislata.banco.business.domain.EntidadBancaria"%>
<%
    EntidadBancariaService entidadBancariaService = new EntidadBancariaServiceImpl();
    int idEntidadBancaria = Integer.parseInt(request.getParameter("idEntidadBancaria"));
    EntidadBancaria EntidadBancaria = entidadBancariaService.get(idEntidadBancaria);

    JsonTransformer jsonTransformerEntidadBancaria = new JsonTransformerImplJackson();
    out.println(jsonTransformerEntidadBancaria.ObjectToJson(EntidadBancaria));

%>

