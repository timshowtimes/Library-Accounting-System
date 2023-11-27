package kz.timshowtime.LibraryBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+",
            message = "Your name should be full and start with capital letters")
    @Column(name = "name")
    private String name;

    @Min(value = 1950, message = "Year of birth should be greater than 1949")
    @Max(value = 2007, message = "Year of birth should be lower than 2008")
    @Column(name = "year_of_birth")
    private int year_of_birth;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person() {}

}
