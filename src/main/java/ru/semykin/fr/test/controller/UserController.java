package ru.semykin.fr.test.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.semykin.fr.test.util.ApplicationConstant.URL_PUBLIC;

@RestController
@RequestMapping(value = "/" + URL_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @GetMapping(value = "/test")
    public String test() {
        return "Hello World Test";
    }
}
