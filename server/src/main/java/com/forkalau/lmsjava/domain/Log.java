package com.forkalau.lmsjava.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Log {

    @Id
    @GeneratedValue
    private Long id;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @PrePersist
    protected void  onCreate() {
        this.date = new Date();
    }

    public Log() {
    }

    public Log(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
