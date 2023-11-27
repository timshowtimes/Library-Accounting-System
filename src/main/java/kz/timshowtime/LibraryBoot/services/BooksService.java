package kz.timshowtime.LibraryBoot.services;

import kz.timshowtime.LibraryBoot.models.Book;
import kz.timshowtime.LibraryBoot.models.Person;
import kz.timshowtime.LibraryBoot.repositories.BookRepo;
import kz.timshowtime.LibraryBoot.repositories.PeopleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BookRepo bookRepo;
    private final PeopleRepo peopleRepo;

    @Autowired
    public BooksService(BookRepo bookRepo, PeopleRepo peopleRepo) {
        this.bookRepo = bookRepo;
        this.peopleRepo = peopleRepo;
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public List<Book> findAllPerPage(int page, int booksPerPage, boolean sort) {
        return !sort ? bookRepo.findAll(PageRequest.of(page, booksPerPage)).getContent() :
                bookRepo.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
    }

    public List<Book> findByNameStartingWithIgnoreCase(String keyword) {
        return bookRepo.findByNameStartingWithIgnoreCase(keyword);
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepo.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepo.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        updateBook.setBook_id(id);
        bookRepo.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepo.deleteById(id);
    }

    @Transactional
    public void setPerson(int person_id, int book_id) {
        Book book = bookRepo.getById(book_id);
        Person person = peopleRepo.getById(person_id);
        book.setPerson(person);
        person.getBooks().add(book);
    }

    @Transactional
    public void undoPerson(Book book) {
        Book removeBook = bookRepo.getById(book.getBook_id());
        removeBook.setPerson(null);
        removeBook.setDateOfReceipt(null);
    }
}
