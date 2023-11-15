package kz.timshowtime.services;

import kz.timshowtime.models.Book;
import kz.timshowtime.models.Person;
import kz.timshowtime.repositories.PeopleRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepo peopleRepo;

    @Autowired
    public PeopleService(PeopleRepo peopleRepo) {
        this.peopleRepo = peopleRepo;
    }

    public List<Person> findAll() {
        return peopleRepo.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepo.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepo.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setPerson_id(id);
        peopleRepo.save(updatePerson);
    }

    @Transactional
    public List<Book> findBooks(int person_id) {
        Optional<Person> person = peopleRepo.findById(person_id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void delete(int id) {
        peopleRepo.deleteById(id);
    }
}
