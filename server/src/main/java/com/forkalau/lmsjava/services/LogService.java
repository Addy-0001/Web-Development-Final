package com.forkalau.lmsjava.services;

import com.forkalau.lmsjava.domain.Log;
import com.forkalau.lmsjava.repositories.ILogRepository;
import com.forkalau.lmsjava.services.iservices.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogService implements ILogService {

    @Autowired
    private ILogRepository logRepository;

    @Override
    public List<Log> findAllLogsByDate (Date createdAt) {
        return logRepository.findByDateContaining(createdAt);
    }
}
