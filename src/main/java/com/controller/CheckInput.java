package com.controller;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckInput{

    public CheckInput() {
    }

    EnumFormat checkInputAdd(String key) {
        String validationString = "[a-zA-Z]+";
        String validationInt = "[0-9]+";
        if (key.matches(validationString)) {
            return EnumFormat.string;
        } else if (key.matches(validationInt)) {
            return EnumFormat.integer;
        }
        return EnumFormat.mix;
    }

    boolean checkLength(int stringKeyLength, String key) throws Exception {
        if (key.length() <= stringKeyLength)
            return true;
        else throw new Exception("Ключ не должен быть больше " + stringKeyLength + " символов ");
    }

}
