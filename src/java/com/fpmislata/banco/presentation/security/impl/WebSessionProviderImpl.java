/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.security.impl;

import com.fpmislata.banco.presentation.security.WebSession;
import com.fpmislata.banco.presentation.security.WebSessionProvider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alumno
 */
public class WebSessionProviderImpl implements WebSessionProvider {

    @Override
    public WebSession getWebSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        HttpSession httpSession = httpServletRequest.getSession();
        WebSession webSession = (WebSession) httpSession.getAttribute("webSession");
        return webSession;

    }

}
