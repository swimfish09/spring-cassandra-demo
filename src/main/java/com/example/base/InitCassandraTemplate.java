package com.example.base;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.cassandra.core.CassandraOperations;

import com.example.repo.PersonRepo;

public class InitCassandraTemplate {
	private static CassandraOperations cassandraOperations;

	public static CassandraOperations instance() {

		if (cassandraOperations == null) {
			cassandraOperations = extracted().getBean("cqlTemplate",
					CassandraOperations.class);
		}
		return cassandraOperations;
	}

	private static ClassPathXmlApplicationContext extracted() {
		return new ClassPathXmlApplicationContext("spring-cassandra.xml");
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new ClassPathResource("spring-cassandra.xml").getPath());
		PersonRepo personRepo = context.getBean(PersonRepo.class);
		System.out.println(personRepo.fetchById("123", "liang"));
		context.close();
	}
}
