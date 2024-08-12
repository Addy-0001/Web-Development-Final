package com.forkalau.lmsjava.services;

import com.forkalau.lmsjava.domain.Admin;
import com.forkalau.lmsjava.repositories.IAdminRepository;
import com.forkalau.lmsjava.services.iservices.IFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    IFactory factory;

    @Transactional
    public Admin loadUserById(Long id){
        Admin admin = adminRepository.getById(id);
        if(admin==null) factory.usernameNotFoundException("Admin not found");
        return admin;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails admin = adminRepository.findByUsername(username);
        if(admin==null) factory.usernameNotFoundException("Admin not found");
        return admin;
    }
}
