package com.forkalau.lmsjava.services.iservices;
import com.forkalau.lmsjava.domain.User;

import java.util.Set;

public interface IUserService {
    Set<User> findAllContainingBarcodeOrName(String barcode, String name);
    User saveOrUpdateUser(User user);
    void deleteUserByIdAndBarcode (Long id, String barcode);
}
