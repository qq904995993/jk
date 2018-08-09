package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-hi")
public interface SchedualServiceHi {
    @GetMapping("/hi")
    String sayHiFromClientOne(@RequestParam("name") String name);
}
