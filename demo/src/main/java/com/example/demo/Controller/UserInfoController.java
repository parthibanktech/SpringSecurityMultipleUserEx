package com.example.demo.Controller;

import com.example.demo.Services.UsersInfoStoreService;
import com.example.demo.module.UsersInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserInfoController {
    @Autowired
    UsersInfoStoreService usersInfoStoreService;


    public UserInfoController() {
        System.out.println("Constructor of UserInfoController is called");
    }

    @GetMapping("/viewUserInfo")
    public List<UsersInfo> addUserInfo()
    {
       return usersInfoStoreService.findAllUserInfo();

    }
    @PostMapping("/addUserInfo")
    public String addUserInfo(@RequestBody List<UsersInfo> usersInfo)
    {
        usersInfoStoreService.saveOrUpdateAll(usersInfo);
        return "Successfully Insterted";
    }

}
