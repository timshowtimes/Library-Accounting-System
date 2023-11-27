package kz.timshowtime.LibraryBoot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
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

    @Getter
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

    public boolean hasPerson() {
        return person != null;
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

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }
}
