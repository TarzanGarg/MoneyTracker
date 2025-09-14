package com.tarzan.MoneyTracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {
    @GetMapping("/")
    public String getRecords(){
        return "hi your records are here!";
    }
}
