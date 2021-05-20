package com.example.HuishoudelijkVoorraadTrackerSpring.entities;

import com.example.HuishoudelijkVoorraadTrackerSpring.domain.IAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    @Getter @Setter
    @Column(name="role")
    private String role;

    public com.example.HuishoudelijkVoorraadTrackerSpring.domain.Account convertToDomain(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, com.example.HuishoudelijkVoorraadTrackerSpring.domain.Account.class);
    }
}
