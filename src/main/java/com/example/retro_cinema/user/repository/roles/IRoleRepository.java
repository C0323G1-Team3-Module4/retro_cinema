package com.example.retro_cinema.user.repository.roles;


import com.example.retro_cinema.user.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Roles, Integer> {
    Roles findById(int id);
}
