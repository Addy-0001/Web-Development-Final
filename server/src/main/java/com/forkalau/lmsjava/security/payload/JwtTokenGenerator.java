package com.forkalau.lmsjava.security.payload;


import com.forkalau.lmsjava.domain.Admin;
import com.forkalau.lmsjava.services.iservices.IFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.forkalau.lmsjava.security.SecurityConstants.EXPIRATION_TIME;
import static com.forkalau.lmsjava.security.SecurityConstants.SECRET;

@Component
public class JwtTokenGenerator {

    @Autowired
    private IFactory factory;

    //Generate the token

    public String generateToken(Authentication authentication) {
        Admin admin = (Admin) authentication.getPrincipal();
        Date now = factory.NewDate(System.currentTimeMillis());
//        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
//

        String adminId = Long.toString(admin.getId());

        Map<String, Object> claims = factory.hashMap();
        claims.put("id", (Long.toString(admin.getId())));
        claims.put("barcode", admin.getUsername());

        return Jwts.builder()
                .setSubject(adminId)
                .setClaims(claims)
                .setIssuedAt(now)
//                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    //Validate the token

    //Get user Id from token
}