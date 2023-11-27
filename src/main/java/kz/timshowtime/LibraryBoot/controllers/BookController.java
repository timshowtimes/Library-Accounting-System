package kz.timshowtime.LibraryBoot.controllers;

import kz.timshowtime.LibraryBoot.models.Book;
import kz.timshowtime.LibraryBoot.models.Person;
import kz.timshowtime.LibraryBoot.services.BooksService;
import kz.timshowtime.LibraryBoot.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    private static List<Book> books;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("bookList", booksService.findAll());
        model.addAttribute("page", 0);
        model.addAttribute("booksPerPage", 1);
        return "books/index";
    }

    @GetMapping(params = {"page", "booksPerPage"})
    public String indexWithParams(@RequestParam("page") int page,
                                  @RequestParam("booksPerPage") int booksPerPage,
                                  @RequestParam(value = "sortingByDate", required = false,
                                  defaultValue = "false") boolean sort, Model model) {
        var bookList = new ArrayList<>(booksService.findAllPerPage(page, booksPerPage, sort));
        model.addAttribute("page", page);
        model.addAttribute("booksPerPage", booksPerPage);
        model.addAttribute("bookList", bookList);
        return "books/index";
    }

    @GetMapping("/sortByDate")
    public String sortingDate(Model model) {
        var books = booksService.findAll();
        books.sort(Comparator.comparing(Book::getYear));
        model.addAttribute("bookList", books);
        model.addAttribute("page", 0);
        model.addAttribute("booksPerPage", 1);
        return "books/index";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Book> searchBooks(@RequestParam("keyword") String keyword) {
        var tempBookList = booksService.findByNameStartingWithIgnoreCase(keyword);
        books = !tempBookList.isEmpty() ? tempBookList : null;
        return books;
    }

    @GetMapping("/getFoundBooks")
    public String findBooksRepresentation(Model model) {
        model.addAttribute("books", books);
        return "/books/founded";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = booksService.findOne(id);
        model.addAttribute("book", book);
        if (book.hasPerson())
            model.addAttribute("person", book.getPerson());
        else {
            model.addAttribute("people", peopleService.findAll());
            model.addAttribute("person", new Person());
        }
        return "books/show";
    }

    @PatchMapping("/{id}/undo")
    public String freeTheBook(@PathVariable("id") int id) {
        booksService.undoPerson(booksService.findOne(id));
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assignPerson(@ModelAttribute("person") Person person,
                               @PathVariable("id") int book_id) {

        booksService.setPerson(person.getPerson_id(), book_id);
        return "redirect:/books/" + book_id;
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("editBook", booksService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{book_id}")
    public String update(@ModelAttribute("editBook") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("book_id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}
