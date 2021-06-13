package com.example.HuishoudelijkVoorraadTrackerSpring.repositories;

import com.example.HuishoudelijkVoorraadTrackerSpring.domain.DRole;
import com.example.HuishoudelijkVoorraadTrackerSpring.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(DRole name);
}
