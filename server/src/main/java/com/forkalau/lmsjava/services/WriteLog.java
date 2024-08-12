package com.forkalau.lmsjava.services;

import com.forkalau.lmsjava.domain.Log;
import com.forkalau.lmsjava.services.iservices.IWriteLog;
import com.forkalau.lmsjava.repositories.ILogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriteLog implements IWriteLog {

    @Autowired
    ILogRepository logRepository;
    private Log log = new Log();


    @Override
    public void saveLog(String message) {
        this.log.setMessage(message);
        logRepository.save(this.log);
    }
}
