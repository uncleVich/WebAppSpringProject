package com.example.controller;

import com.example.model.Person;
import com.example.sevice.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("person")
public class PersonUpdateController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/updatePerson/{id}", method = RequestMethod.GET)
    public String selectForUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "personUpdate";
    }

    @RequestMapping(value = "/updatePersonFailed", method = RequestMethod.GET)
    public String updatePersonFailed() {
        return "personUpdate";
    }

    @RequestMapping(value = "/updatePerson", method = RequestMethod.POST)
    public String performUpdate(@ModelAttribute Person person, RedirectAttributes redirectAttributes,
                                SessionStatus sessionStatus) {
        String message = null;
        String viewName = null;
        try {
            personService.update(person);
            message = "Person updated. Person id :" + person.getId();
            viewName = "redirect:/mvc/listPerson";
            sessionStatus.setComplete();
        } catch (Exception e) {
            message = "Person update failed. ";
            viewName = "redirect:/mvc/updatePersonFailed";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return viewName;
    }
}
