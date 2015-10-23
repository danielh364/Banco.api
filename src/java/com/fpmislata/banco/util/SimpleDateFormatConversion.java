/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author danie
 */
public class SimpleDateFormatConversion {

    public SimpleDateFormatConversion() {
        
 
    }
    public Date setFecha(String fecha){
           SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaFormatoDate;
        try {
            fechaFormatoDate = fechaFormato.parse(fecha);
        } catch (ParseException ex) {
            throw new RuntimeException("error al convertir la fecha"+ex.getMessage());
        }

        return  fechaFormatoDate;
    }
    
    
    
}
