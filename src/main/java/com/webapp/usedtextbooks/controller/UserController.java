//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.controller;
import com.webapp.usedtextbooks.model.Users;
import com.webapp.usedtextbooks.repository.UsersRepository;
import com.webapp.usedtextbooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public UserService userService;

    @GetMapping
    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody Users users){
        return userService.addUser(users);
    }

}
