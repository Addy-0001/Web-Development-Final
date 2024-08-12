package com.forkalau.lmsjava.services.validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;



@Service
public class MapValidationError implements IMapValidationError {

    private String errorString;

    @Override
    public ResponseEntity<?> mapValidationError(BindingResult result) {
        setErrorString("");
        if (result.hasErrors()) {
            errorStringPut(result);
            return new ResponseEntity(errorString, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private void errorStringPut (BindingResult result) {
        for (FieldError error : result.getFieldErrors()) {
            errorString = errorString.substring(0, errorString.length()) + error.getDefaultMessage() + " | ";
        }
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
