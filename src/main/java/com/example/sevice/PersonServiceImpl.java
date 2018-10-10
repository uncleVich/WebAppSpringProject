package com.example.sevice;

import com.example.dao.PersonDao;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private PersonDao personDao;

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }

    public Person findById(Long id) {
        return personDao.findById(id);
    }

    public void create(Person person) {
        personDao.create(person);
    }

    public void update(Person person) {
        personDao.update(person);
    }

    public void delete(Long id) {
        personDao.delete(id);
    }
}
