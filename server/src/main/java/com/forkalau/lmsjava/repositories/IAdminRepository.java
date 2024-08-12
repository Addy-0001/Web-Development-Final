package com.forkalau.lmsjava.repositories;

import com.forkalau.lmsjava.domain.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends CrudRepository<Admin, Long> {


    Admin findByUsername(String username);
    Admin getById(Long id);
}
