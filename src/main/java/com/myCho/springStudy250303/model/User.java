package com.myCho.springStudy250303.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private String role;
    /*
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();
    */
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)  // orphanRemoval = true 지워지는 속성
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)  //one 으로 끝나는 것은 Eager, many로 끝나는 것은 Lazy
    private List<Board> boards = new ArrayList<>();
 }
