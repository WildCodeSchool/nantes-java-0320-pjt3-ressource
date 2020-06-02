package com.wildcodeschool.ressource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/result")
    public String result() {
        return "/result";
    }

    @GetMapping("/product")
    public String product() {

        return "/product";
    }
}
