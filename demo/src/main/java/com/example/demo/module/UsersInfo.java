package com.example.demo.module;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsersInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer ID;

    private String userName;

    private String passWord;

    private String userRole;

    @Version
    private Integer version;  // Add this for optimistic locking

    // getters, setters, constructors (can use Lombok as you have)
}