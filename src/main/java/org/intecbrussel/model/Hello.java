package org.intecbrussel.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class Hello {
    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }

    //testing
}
