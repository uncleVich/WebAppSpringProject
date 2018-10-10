package com.example.controller;

import com.example.sevice.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PersonListController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/listPerson", method = RequestMethod.GET)
    public String findAllPersons(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "personList";
    }
}
