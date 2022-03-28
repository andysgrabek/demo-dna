package com.andysgrabek.dna.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<ID> extends JpaRepository<User, ID> {

}