package com.fpmislata.banco.presentation.Json;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumno
 */
public interface JsonTransformer {
    
    String ObjectToJson(Object data);
    <T> T fromJsonToObject(String json, Class <T> clazz);
    
}
