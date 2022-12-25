package ua.com.owu.lessonsspring.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String login;

    private String password;

    private String role = "ROLE_CLIENT";
}
