package com.example.sevice;

import com.example.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Person findById(Long id);
    void create(Person person);
    void update(Person person);
    void delete(Long id);
}
