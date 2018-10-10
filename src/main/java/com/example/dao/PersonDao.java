package com.example.dao;

import com.example.model.Person;

import java.util.List;

public interface PersonDao {

    List<Person> findAll();
    Person findById(Long id);
    void create(Person person);
    void update(Person person);
    void delete(Long id);
}
