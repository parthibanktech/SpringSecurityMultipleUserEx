package com.example.demo.Services;

import com.example.demo.Repository.UserInfoRepo;
import com.example.demo.module.UsersInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersInfoStoreService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserInfoRepo userInfoRepo;

    @Autowired
    public UsersInfoStoreService(UserInfoRepo userInfoRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userInfoRepo = userInfoRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Saves or updates a list of users safely.
     * For existing users, it fetches fresh entity from DB and updates.
     * If user ID not found, treats as new user.
     * For new users (ID = 0), saves directly.
     */
    @Transactional
    public void saveOrUpdateAll(List<UsersInfo> usersInfo) {
        if (usersInfo == null || usersInfo.isEmpty()) {
            throw new IllegalArgumentException("Users list cannot be null or empty");
        }

        for (UsersInfo user : usersInfo) {
            if (user.getPassWord() == null || user.getPassWord().isEmpty()) {
                throw new IllegalArgumentException("Password cannot be null or empty for user: " + user.getUserName());
            }

            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassWord());

            if (user.getID() != null && user.getID() != 0) {
                UsersInfo existingUser = userInfoRepo.findById(user.getID()).orElse(null);

                if (existingUser != null) {
                    existingUser.setUserName(user.getUserName());
                    existingUser.setPassWord(encodedPassword);
                    userInfoRepo.save(existingUser);
                } else {
                    // Treat as new user if ID not found
                    user.setPassWord(encodedPassword);
                    userInfoRepo.save(user);
                }
            } else {
                user.setPassWord(encodedPassword);
                userInfoRepo.save(user);
            }
        }
    }

    public List<UsersInfo> findAllUserInfo() {


        return  userInfoRepo.findAll();
    }
}
