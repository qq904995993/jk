package com.example.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AService {

	@Cacheable(value="user", key="'test'")
    public String getUser() {
		System.out.println("no cache");
		return "123456";
	}
}
