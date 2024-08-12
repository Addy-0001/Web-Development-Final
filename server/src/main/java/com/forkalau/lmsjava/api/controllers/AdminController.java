package com.forkalau.lmsjava.api.controllers;


import com.forkalau.lmsjava.domain.Admin;
import com.forkalau.lmsjava.security.payload.JWTLoginSucessReponse;
import com.forkalau.lmsjava.security.payload.JwtTokenGenerator;
import com.forkalau.lmsjava.security.payload.LoginRequest;
import com.forkalau.lmsjava.services.RegisterService;
import com.forkalau.lmsjava.services.validation.MapValidationError;
import com.forkalau.lmsjava.services.iservices.IFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.forkalau.lmsjava.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/admin")
public class AdminController {

        @Autowired
        private MapValidationError mapValidationErrorService;

        @Autowired
        private RegisterService registerService;

        @Autowired
        private JwtTokenGenerator tokenProvider;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private IFactory factory;



        @PostMapping("/login")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
            ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
            if(errorMap != null) return errorMap;
            Authentication authentication = authenticationManager.authenticate(
                    factory.usernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            System.out.println(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
        }

//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody Admin admin, BindingResult result){
////         Validate passwords match
////        userValidator.validate(admin,result);
//
////        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
////        if(errorMap != null)return errorMap;
//
//        Admin newAdmin = registerService.saveAdmin(admin);
//
//        return  new ResponseEntity<Admin>(newAdmin, HttpStatus.CREATED);
//    }
}
