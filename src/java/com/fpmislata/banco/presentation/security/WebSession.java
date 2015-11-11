/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.security;

import com.fpmislata.banco.business.domain.Usuario;
import java.util.Date;

/**
 *
 * @author alumno
 */
public class WebSession {
    Usuario usuario;
    Date Fecha;

    public WebSession() {
    }

    public WebSession(Usuario usuario, Date Fecha) {
        this.usuario = usuario;
        this.Fecha = Fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }
}
