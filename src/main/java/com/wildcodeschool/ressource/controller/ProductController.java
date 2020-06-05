package com.wildcodeschool.ressource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @GetMapping("/results")
    public String result() {
        return "results";
    }

    @PostMapping("/results")
    public String postResult() {
        return "results";
    }

    @GetMapping("/product")
    public String product() {

        return "product";
    }
}
