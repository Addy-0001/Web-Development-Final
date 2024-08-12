package com.forkalau.lmsjava.services;

import com.forkalau.lmsjava.domain.User;
import com.forkalau.lmsjava.services.iservices.IFactory;
import com.forkalau.lmsjava.services.iservices.IUserService;
import com.forkalau.lmsjava.services.iservices.IWriteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.forkalau.lmsjava.repositories.IUserRepository;

import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IFactory factory;
    @Autowired
    private IWriteLog writeLog;

    @Override
    public Set<User> findAllContainingBarcodeOrName(String barcode, String name) {
        Set<User> userSet = userRepository.findByBarcodeContainingOrNameContaining(barcode, name);
        return userSet;
    }

    @Override
    public User saveOrUpdateUser(User user) {
        try {
            User user1 = userRepository.save(user);
            writeLog.saveLog("done");
            return user1;
        } catch (Exception e) {
            throw factory.ThrowException("User barcode: '" + user.getBarcode() + "' already exist");
        }
    }

    @Override
    public void deleteUserByIdAndBarcode (Long id, String barcode) {
        User user = userRepository.findByBarcode(barcode);
        if (user == null || user.getId() != id)
            throw factory.ThrowException("Cannot find user.");
        userRepository.delete(user);
        writeLog.saveLog("done");
    }
}
