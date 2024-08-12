package com.forkalau.lmsjava.services.iservices;

import com.forkalau.lmsjava.domain.Author;
import com.forkalau.lmsjava.domain.Loan;
import com.forkalau.lmsjava.services.exceptions.CustomException;
import com.forkalau.lmsjava.security.payload.LoginInvalidResponse;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public interface IFactory {
    Author CreateAuthor ();
    Loan CreateLoan ();
    Date NewDate ();
    Date NewDate (Long date);
    Map hashMap();
    SimpleDateFormat simpleDateFormat(String pattern);
    CustomException ThrowException (String message);
    ResponseEntity responseEntity(Object body, HttpStatus status);
    LoginInvalidResponse invalidLoginResponse();
    Gson gson();
    BCryptPasswordEncoder bcryptPasswordEncoder();
    UsernameNotFoundException usernameNotFoundException(String ex);
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken(String username, String password);
}
