package com.example.BlogApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends Base{
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL )
    @JoinTable(name = "Users_Roles" ,
            joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "id" ) ,
            inverseJoinColumns = @JoinColumn(name = "role_id" , referencedColumnName = "id"))
    private Set<Role> roles;
}
