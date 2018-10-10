package com.example.controller;

import com.example.model.Person;
import com.example.sevice.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("person")
public class PersonCreateController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/createPerson", method = RequestMethod.GET)
    public String startCreatingNewPerson(Model model) {
        model.addAttribute("person", new Person());
        return "personCreate";
    }

    @RequestMapping(value = "/createPersonFailed", method = RequestMethod.GET)
    public String createPersonFailed() {
        return "personCreate";
    }
    @RequestMapping(value = "/createPerson", method = RequestMethod.POST)
    public String performCreate(@ModelAttribute Person person, RedirectAttributes redirectAttributes,
                                SessionStatus sessionStatus) {
        String message = null;
        String viewName = null;
        try {
            personService.create(person);
            message = "Person created. Person id :" + person.getId();
            viewName = "redirect:/mvc/listPerson";
            sessionStatus.setComplete();
        } catch (Exception ex) {
            message = "Person create failed";
            viewName = "redirect:/mvc/createPersonFailed";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return viewName;
    }
}
