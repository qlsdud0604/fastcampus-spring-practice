package com.fastcampus.spring;

import com.fastcampus.spring.config.Config;
import com.fastcampus.spring.logic.BubbleSort;
import com.fastcampus.spring.logic.Sort;
import com.fastcampus.spring.service.SortService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        SortService sortService = context.getBean(SortService.class);

        System.out.println("[result] : " + sortService.doSort(Arrays.asList(args)));
    }
}
