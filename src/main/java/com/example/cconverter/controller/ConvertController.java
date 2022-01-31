package com.example.cconverter.controller;

import com.example.cconverter.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConvertController {

    @Autowired
    private ConvertService service;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Usage: /convert?amount=<...>&source=<currency code>&target=<currency code>";
    }

    @RequestMapping("/convert")
    @ResponseBody
    double convert(@RequestParam double amount, @RequestParam String source, @RequestParam String target) {
        return service.convert(amount, source, target);
    }

}
