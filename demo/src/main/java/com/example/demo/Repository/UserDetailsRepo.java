package com.example.demo.Repository;

import com.example.demo.module.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepo extends JpaRepository<UsersInfo, Integer> {

   Optional<UsersInfo> getByUserName(String name);
}
