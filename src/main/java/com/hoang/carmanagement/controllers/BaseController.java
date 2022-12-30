package com.hoang.carmanagement.controllers;

import jakarta.persistence.Inheritance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(BaseController.BASE_URL)
public class BaseController {
    public static final String BASE_URL = "/api/v1";
}
