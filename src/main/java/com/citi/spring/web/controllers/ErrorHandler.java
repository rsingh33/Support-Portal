package com.citi.spring.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    private static Logger logger = Logger.getLogger(ErrorHandler.class);


    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseException(DataAccessException ex) {

        ex.printStackTrace();
        logger.error("Data Access Exception occured");
        logger.error(ex.getStackTrace());
        return "error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex) {
        ex.printStackTrace();
        logger.error("Access Denied Exception occured");
        logger.error(ex.getStackTrace());
        return "denied";
    }
}
