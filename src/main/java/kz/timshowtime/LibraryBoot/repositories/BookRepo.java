package kz.timshowtime.LibraryBoot.repositories;

import kz.timshowtime.LibraryBoot.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByNameStartingWithIgnoreCase(String keyword);
}
