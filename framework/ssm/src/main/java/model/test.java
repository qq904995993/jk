package model;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class test {

	@PostConstruct
	public void tset() {
		System.out.print("test is a bean!");
	}
}
