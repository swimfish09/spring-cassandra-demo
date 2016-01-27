package com.example.repo;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.pojo.Person;

public interface PersonRepo extends CrudRepository<Person, String> {
	@Query("Select * from person where id=?0 and name=?1")
	public Person fetchById(String id, String name);
}
