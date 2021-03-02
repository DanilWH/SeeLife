package com.example.SeeLife.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllersUtils {

    public static Map<String, String> getErrors(BindingResult bindingResult) {
        /*
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );


        return bindingResult.getFieldErrors().stream().collect(collector);
         */

        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for(FieldError fieldError : fieldErrors) {
            // a field is a key and a message is a value for the Map.
            String fieldKey = fieldError.getField() + "Error";
            String messageValue = fieldError.getDefaultMessage();

            // merge two value if the Map contains the same key.
            // or just put a new set of key and value in the Map.
            if (errors.containsKey(fieldKey)) {
                errors.merge(fieldKey, messageValue, (v1, v2) -> v1 + "\n" + v2);
            } else {
                errors.put(fieldKey, messageValue);
            }
        }

        return errors;
    }

}
