package com.haiph.userservice.entity.person;

import com.haiph.userservice.config.Salary;
import com.haiph.userservice.entity.person.Person;
import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Setter

@PrimaryKeyJoinColumn(name = "id")
public class Employee extends Person {
    @Enumerated(EnumType.ORDINAL)
    private Position position;
    private Float salary;
    private String cmnd;

    public Position getPosition() {
        return position;
    }

    public Float getSalary() {
        float salaryEmployee = 0f;
        switch (this.position){
            case CHEF:
                salaryEmployee = Salary.CHEF;
                break;
            case SERVICE:
                salaryEmployee =Salary.SERVICE;
                break;
            case SERVE:
                salaryEmployee = Salary.SERVE;
                 break;
            case MANAGER:
                salaryEmployee =Salary.MANAGER;
                break;
        }
        return salaryEmployee;
    }


    public String getCmnd() {
        return cmnd;
    }

    public enum Position{
        SERVE,CHEF,MANAGER,SERVICE
    }
    @PrePersist
    public void prePersit() {
        if (this.role == null) {
            this.role = Role.EMPLOYEE;
        }
    }



}
