package com.myCho.springStudy250303.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    /*
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;
    */
}
