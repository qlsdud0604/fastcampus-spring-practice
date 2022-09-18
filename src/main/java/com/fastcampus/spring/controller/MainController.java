package com.fastcampus.spring.controller;

import com.fastcampus.spring.service.SortService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private final SortService sortService;

    public MainController(SortService sortService) {
        this.sortService = sortService;
    }

    @GetMapping("/")
    public String sort(@RequestParam List<String> list) {
        return sortService.doSort(list).toString();
    }
}
