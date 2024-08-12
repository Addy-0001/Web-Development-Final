package com.forkalau.lmsjava.api.controllers;

import com.forkalau.lmsjava.domain.User;
import com.forkalau.lmsjava.services.iservices.IFactory;
import com.forkalau.lmsjava.services.iservices.IUserService;
import com.forkalau.lmsjava.services.validation.IMapValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IFactory factory;
    @Autowired
    private IMapValidationError mapValidationError;

    @GetMapping("/search")
    public ResponseEntity<Set<User>> getUserByBarcodeOrName(@RequestParam String barcode, String name) {
        Set<User> userSet= userService.findAllContainingBarcodeOrName(barcode, name);
        return factory.responseEntity(userSet, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationError.mapValidationError(result);
        if (errorMap != null) return errorMap;
        User user1 = userService.saveOrUpdateUser(user);
        return factory.responseEntity(user1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long id, String barcode) {
        userService.deleteUserByIdAndBarcode(id, barcode);
        return factory.responseEntity("User with ID:'" + id + "'was deleted.", HttpStatus.OK);
    }
}
