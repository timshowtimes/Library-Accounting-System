package kz.timshowtime.LibraryBoot.controllers;

import kz.timshowtime.LibraryBoot.models.Person;
import kz.timshowtime.LibraryBoot.services.PeopleService;
import kz.timshowtime.LibraryBoot.utils.PersonNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final PersonNameValidator nameValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonNameValidator nameValidator) {
        this.peopleService = peopleService;
        this.nameValidator = nameValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int person_id, Model model) {
        model.addAttribute("person", peopleService.findOne(person_id));
        model.addAttribute("bookList", peopleService.findBooks(person_id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newClient(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("editPerson", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{person_id}")
    public String update(@ModelAttribute("editPerson") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("person_id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        nameValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
