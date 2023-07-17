package com.example.smartbeautymirror.AppUser;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

    @Id
    @Column(unique = true, columnDefinition = "VARCHAR(20)")
    private String email;
    private String password;
    private String name;
    private String phone;
    @Column(unique = true)
    private String nickname;
}
