package com.forkalau.lmsjava.services.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IMapValidationError {
    ResponseEntity<?> mapValidationError(BindingResult result);
}
