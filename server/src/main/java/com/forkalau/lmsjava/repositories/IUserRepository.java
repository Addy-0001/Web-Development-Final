package com.forkalau.lmsjava.repositories;


import com.forkalau.lmsjava.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

    User findByBarcode(String barcode);

    Set<User>  findByBarcodeContainingOrNameContaining(String barcode, String name);
}
