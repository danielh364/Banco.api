/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.database;

import com.fpmislata.banco.persistence.jdbc.migration.DatabaseMigration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author alumno
 */
public class ServerContextListenerImpl implements ServletContextListener {


    @Autowired
    DatabaseMigration databaseMigration;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Iniciando");
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        AutowireCapableBeanFactory autowireCapableBeanFactory = webApplicationContext.getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);

        databaseMigration.migrate();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("desconectando");
        
    }
}
