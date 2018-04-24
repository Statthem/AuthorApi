package com.statthem.authorapi.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	Logger logger = LogManager.getLogger("CONSOLE_JSON_APPENDER");
	Logger fileLogger = LogManager.getLogger("ASYNC_JSON_FILE_APPENDER");
	Logger debugLogger = LogManager.getLogger("DEBUG");
	Logger classLogger = LogManager.getLogger(HelloController.class);
	

    @RequestMapping("/")
    public String index() {
    	logger.debug("Debug message");
    	logger.error("error message");
    	logger.info("ttt");
    	fileLogger.info("info");
    	fileLogger.error("error");
    	
    	classLogger.debug("debug");
    	classLogger.info("wdwdwdwd");
    	
    	
        return "Greetings from Spring Boot!";
        
    }

}