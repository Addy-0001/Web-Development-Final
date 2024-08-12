package com.forkalau.lmsjava.services.iservices;

import com.forkalau.lmsjava.domain.Book;
import com.forkalau.lmsjava.domain.Log;

import java.util.Date;
import java.util.List;

public interface ILogService {
    List<Log> findAllLogsByDate (Date createdAt);

}
