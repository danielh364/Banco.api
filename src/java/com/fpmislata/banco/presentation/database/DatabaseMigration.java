/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.database;

/**
 *
 * @author danie
 */
public interface DatabaseMigration {
    void migrate(String datasourceName,String packageName);
}
