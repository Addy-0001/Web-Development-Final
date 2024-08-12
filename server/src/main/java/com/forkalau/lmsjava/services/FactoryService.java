package com.forkalau.lmsjava.services;

import com.forkalau.lmsjava.domain.Author;
import com.forkalau.lmsjava.domain.Loan;
import com.forkalau.lmsjava.services.iservices.IFactory;
import com.forkalau.lmsjava.services.exceptions.CustomException;
import com.forkalau.lmsjava.security.payload.LoginInvalidResponse;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FactoryService implements IFactory {

    @Override
    public Author CreateAuthor () {
        return new Author();
    }
    @Override
    public Loan CreateLoan () {
        return new Loan();
    }
    @Override
    public Date NewDate() { return new Date(); }
    @Override
    public Date NewDate(Long date) { return new Date(date); }

    @Override
    public Map hashMap() { return new HashMap<>(); }

    @Override
    public SimpleDateFormat simpleDateFormat(String pattern) { return new SimpleDateFormat(pattern);}

    @Override
    public CustomException ThrowException(String message) { return new CustomException(message);}

    @Override
    public ResponseEntity<?> responseEntity(Object body, HttpStatus status) {
        return new ResponseEntity(body, status);
    }

    @Override
    public LoginInvalidResponse invalidLoginResponse() { return new LoginInvalidResponse(); }

    @Override
    public Gson gson() {return new Gson();}

    @Override
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    public UsernameNotFoundException usernameNotFoundException(String ex) { return new UsernameNotFoundException(ex);}

    @Override
    public UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(String username, String password) {
       return new UsernamePasswordAuthenticationToken(username, password);
    }

}
