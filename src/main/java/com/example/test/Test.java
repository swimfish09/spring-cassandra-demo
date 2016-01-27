package com.example.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.example.pojo.Person;
import com.example.repo.PersonRepo;

public class Test {
	public static ClassPathXmlApplicationContext context;
	static {
		context = new ClassPathXmlApplicationContext(new ClassPathResource(
				"spring-cassandra.xml").getPath());
	}

	public static void main(String[] args) {
		PersonRepo personRepo = context.getBean(PersonRepo.class);
		Person person = personRepo.fetchById("123", "liang");
		System.out.println(person.toString());
	}
}
