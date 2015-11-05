/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.database.impl;

import com.fpmislata.banco.persistence.jdbc.DataSourceFactory;
import com.fpmislata.banco.presentation.database.DatabaseMigration;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author danie
 */
public class DatabaseMigrationImplFlyWay implements DatabaseMigration {

    @Autowired
    DataSourceFactory dataSourceFactory;

    @Override
    public void migrate() {
        Flyway flyway = new Flyway();
        DataSource dataSource = dataSourceFactory.getDataSource("banco");
        flyway.setDataSource(dataSource);
        flyway.setLocations("com.fpmislata.banco.presentation.database.scripts");
        flyway.setEncoding("UTF-8");
        flyway.migrate();
    }

}
