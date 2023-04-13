package com.haiph.userservice.entity.person;

import jakarta.persistence.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.ws.rs.DefaultValue;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class User extends Person {
    private String imgUrl;
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;
    @DefaultValue("0")
    private Integer countLogin;

    @PrePersist
    public void prePersit() {
        if (this.role == null) {
            this.role = Role.USER;
        }
    }

    public enum UserType{
        VIP,POTENTIAL,NORMAL
    }
}
