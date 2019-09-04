package com.itedu.service;

import java.util.List;

import com.itedu.bean.Person;

public interface PersonService {

	void save(Person person);

	void update(Person person);

	Person getPerson(Integer personid);

	void delete(Integer personid);

	List<Person> getPersons();

}