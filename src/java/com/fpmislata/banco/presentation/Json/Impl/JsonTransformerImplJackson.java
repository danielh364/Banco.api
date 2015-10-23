/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.banco.presentation.Json.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.banco.presentation.Json.JsonTransformer;
import java.io.IOException;

/**
 *
 * @author alumno
 */
public class JsonTransformerImplJackson implements JsonTransformer {

    @Override
    public String ObjectToJson(Object data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public  <T> T fromJsonToObject(String json, Class <T> clazz) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(json, clazz);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
