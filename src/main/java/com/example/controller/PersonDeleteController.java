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
public class PersonDeleteController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/deletePerson/{id}", method = RequestMethod.GET)
    public String selectForDelete(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "personDelete";
    }

    @RequestMapping(value = "/deletePersonFailed", method = RequestMethod.POST)
    public String deletePersonFailed() {
        return "personDelete";
    }

    @RequestMapping(value = "/deletePerson", method = RequestMethod.POST)
    public String delete(@ModelAttribute Person person, RedirectAttributes redirectAttributes,
                         SessionStatus sessionStatus) {
        String message = null;
        String viewName =null;
        try {
            personService.delete(person.getId());
            message = "Person deleted. Person id :" + person.getId();
            viewName = "redirect:/mvc/listPerson";
            sessionStatus.setComplete();
        } catch (Exception e) {
            message = "Person delete failed.";
            viewName = "redirect:/mvc/deletePersonFailed";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return viewName;
    }

}
