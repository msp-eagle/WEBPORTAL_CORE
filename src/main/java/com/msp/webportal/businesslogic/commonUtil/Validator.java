package com.msp.webportal.businesslogic.commonUtil;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Validator {


    public static Map<String, Set<String>> create(BindingResult bindingResult) {
        Map<String, Set<String>> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String code = fieldError.getCode();
            String field = fieldError.getField();
            if (code.equals("NotBlank") || code.equals("NotNull") || code.equals("NotEmpty")) {
                errors.computeIfAbsent(field, key -> new HashSet<>()).add("required");
            } else if (code.equals("Email") && field.equals("email")) {
                errors.computeIfAbsent(field, key -> new HashSet<>()).add("email");
            } else if (code.equals("Size") && field.equals("mobile")) {
                errors.computeIfAbsent(field, key -> new HashSet<>()).add("pattern");
            } else if (code.equals("Pattern") && field.equals("mobile")) {
                errors.computeIfAbsent(field, key -> new HashSet<>()).add("pattern");
            }
        }
        return errors;
    }

    public  static Map<String, Set<String>> create(BindingResult bindingResult,String fieldName,String isDuplicate) {
        Map<String, Set<String>> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String code = fieldError.getCode();
            String field = fieldError.getField();
            if (code.equals("NotBlank") || code.equals("NotNull") || code.equals("NotEmpty")) {
                errors.computeIfAbsent(field, key -> new HashSet<>()).add("required");
            } else if (code.equals("Email")) {
                errors.computeIfAbsent(field, key -> new HashSet<>()).add("email");
            } else if (code.equals("Pattern")) {
                errors.computeIfAbsent(field, key -> new HashSet<>()).add("pattern");
            } else if (code.equals("Size")) {
                errors.computeIfAbsent(field, key -> new HashSet<>()).add("size");
            }

        }
        if ((!fieldName.equals("All"))&&(!isDuplicate.equals("No"))) {
            errors.computeIfAbsent(fieldName, key -> new HashSet<>()).add("duplicate");
        }

        return errors;
    }

}
