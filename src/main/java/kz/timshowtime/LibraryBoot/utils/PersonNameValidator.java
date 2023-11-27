package kz.timshowtime.LibraryBoot.utils;

import kz.timshowtime.LibraryBoot.models.Person;
import kz.timshowtime.LibraryBoot.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonNameValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonNameValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.findOne(person.getPerson_id()) != null) {
            errors.rejectValue("name", "",
                    "A person with this name is already in the database");
        }
    }

}
