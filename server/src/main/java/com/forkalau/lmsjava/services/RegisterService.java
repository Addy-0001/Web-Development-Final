package com.forkalau.lmsjava.services;

import com.forkalau.lmsjava.domain.Admin;
import com.forkalau.lmsjava.repositories.IAdminRepository;
import com.forkalau.lmsjava.services.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
        @Autowired
        private IAdminRepository adminRepository;


        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        public Admin saveAdmin (Admin newAdmin){

            try{
                newAdmin.setPassword(bCryptPasswordEncoder.encode(newAdmin.getPassword()));
                //Username has to be unique (exception)
                newAdmin.setUsername(newAdmin.getUsername());
                // Make sure that password and confirmPassword match
                // We don't persist or show the confirmPassword
                newAdmin.setConfirmPassword("");
                return adminRepository.save(newAdmin);

            }catch (Exception e){
                throw new CustomException("Username '"+newAdmin.getUsername()+"' already exists");
            }

        }



    }
