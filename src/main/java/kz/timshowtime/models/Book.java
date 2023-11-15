package kz.timshowtime.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @Column(name = "name")
    @NotEmpty(message = "Name of book should not be empty")
    private String name;

    @Column(name = "author")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+",
            message = "Author name should consist first name and last name and start with capital letters")
    private String author;

    @Column(name = "year")
    @Min(value = 1400, message = "Year should be greater than 1399")
    @Max(value = 2023, message = "Year should be lower than 2024")
    private int year;

    @Column(name = "dateOfReceipt")
    private LocalDate dateOfReceipt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Book() {};

    public void setDateOfReceipt(LocalDate dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public boolean hasPerson() {
        return person != null;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.dateOfReceipt = LocalDate.now();
        this.person = person;
    }

    @JsonIgnore
    public boolean isExpired() {
        return dayBeforeTheDelay() > 10;
    }

    public int dayBeforeTheDelay() {
        return (int) ChronoUnit.DAYS.between(this.dateOfReceipt, LocalDate.now());
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }
}
