package com.haiph.userservice.entity.person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;

import java.util.UUID;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column
    protected UUID id;
    @Column(unique = true, name = "person_code")
    protected String personCode;
    @Column(unique = true)
    protected String username;
    @Column
    protected String password;
    @Column(unique = true)
    protected String email;
    @Column
    protected String phoneNumber;
    @Column
    protected String address;
    @Column
    @Enumerated(EnumType.ORDINAL)
    protected Gender gender;
    @Column
    @Enumerated(EnumType.ORDINAL)
    protected Status status;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;
    @Column
    @Formula("concat(first_name, ' ', last_name)")
    protected String fullName;
    @Column
    @Enumerated(EnumType.STRING)
    protected Role role;
    @Column
    @CreationTimestamp
    protected LocalDate createdDate;


    public String getFullName() {
        return this.fullName = this.firstName + " " + this.lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = this.firstName + " " + this.lastName;
    }

    public Person() {
    }

    public enum Gender {
        MALE, FEMALE
    }

    public enum Status {
        NOT_ACTIVE, ACTIVE;
    }

    public enum Role {
        USER, ADMIN, EMPLOYEE
    }

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = Status.NOT_ACTIVE;
        }
        if (this.fullName == null) {
            this.fullName = this.firstName + " " + this.lastName;
        }
    }
}
