package jk.utils;

import org.springframework.validation.BindingResult;

public class ValidUtils {

    public static String getValidMessage(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return null;
    }

}
