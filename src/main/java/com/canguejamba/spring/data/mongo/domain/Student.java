/**
 * Created by IntelliJ IDEA.
 * User: Cangue jamba
 * Project name: spring-data-mongo
 * To change this template use File | Settings | File and Code Templates.
 */

package com.canguejamba.spring.data.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Student {
    @Id
    private String id;
    
    @NotNull(message = "first Name cannot be null")
    private String firstname;
    private String lastname;
    private String fullName;
    @NotNull(message = "email cannot be null")
    @Email(message = "is not valid email")
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;

    public Student(String firstname, String lastname, String email, Gender gender, Address address, List<String> favouriteSubjects, BigDecimal totalSpentInBooks, LocalDateTime created) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favouriteSubjects = favouriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
        this.created = created;
        this.fullName = firstname +" "+lastname;
    }
}
