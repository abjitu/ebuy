package com.ebuy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.commons.response.Response;
import com.crossover.commons.response.Response.ResultType;


/**
 * The Class WelcomeController.
 */
@RestController
public class WelcomeController {

    /** The logger. */
    private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    /**
     * Hello.
     *
     * @return the response
     */
    @RequestMapping("/")
    public Response hello() {
        logger.debug("Entering hello");
        return Response.get(ResultType.SUCCESS, "Application is up and running");
    }

}
