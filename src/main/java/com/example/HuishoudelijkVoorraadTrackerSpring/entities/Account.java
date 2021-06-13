package com.example.HuishoudelijkVoorraadTrackerSpring.entities;

import com.example.HuishoudelijkVoorraadTrackerSpring.domain.IAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account implements IAccount {
    @Id
    @GeneratedValue
    @Column(name="id")
    @Getter @Setter
    private Long id;
    @Getter @Setter
    @Column(name="username")
    private String username;
    @Getter @Setter
    @Column(name="password")
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public com.example.HuishoudelijkVoorraadTrackerSpring.domain.Account convertToDomain(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, com.example.HuishoudelijkVoorraadTrackerSpring.domain.Account.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return username.equals(account.username);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
