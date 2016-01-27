package com.example.springCassandra;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.pojo.Person;

public class CassandraApp {
	public static Logger logger = LoggerFactory.getLogger(CassandraApp.class);
	private static Cluster cluster;
	private static Session session;

	public static void main(String[] args) {
		String username = "cassandra";
		String password = "cassandra";
		cluster = Cluster.builder().addContactPoint("192.168.1.128")
				.withCredentials(username, password).build();
		session = cluster.connect("showcase");
		CassandraOperations cassandraOperations = new CassandraTemplate(session);
		cassandraOperations.insert(new Person("123", "liang", "boy", 12));
		Select select = QueryBuilder.select().from("person");
		logger.info(select.getQueryString());
		List<Person> persons = cassandraOperations.select(select, Person.class);
		for (Person person : persons) {
			System.out.println(person.toString());
		}
	}
}
