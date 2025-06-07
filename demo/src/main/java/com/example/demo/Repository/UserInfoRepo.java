package com.example.demo.Repository;

import com.example.demo.module.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UsersInfo, Integer> {


}
