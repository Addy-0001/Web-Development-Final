package com.forkalau.lmsjava.repositories;

import com.forkalau.lmsjava.domain.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ILogRepository  extends CrudRepository <Log, Long>{
    List<Log> findByDateContaining(Date date);

}
