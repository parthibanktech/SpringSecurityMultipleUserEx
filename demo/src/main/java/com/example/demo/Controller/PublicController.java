package com.example.demo.Controller;

import com.example.demo.Services.UsersInfoStoreService;
import com.example.demo.module.UsersInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Public")
public class PublicController {

    @Autowired
    UsersInfoStoreService usersInfoStoreService;


    public PublicController() {
        System.out.println("Constructor of UserInfoController is called");
    }

    @GetMapping("/viewCourseInfo")
    public String addUserInfo()
    {
        return "AI, ML , IO ";

    }
}
