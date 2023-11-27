package kz.timshowtime.LibraryBoot.repositories;

import kz.timshowtime.LibraryBoot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepo extends JpaRepository<Person, Integer> {
}
