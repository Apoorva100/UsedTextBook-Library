//Group 12
//SUID: 640608313
//NETID: rjain10@syr.edu

package com.webapp.usedtextbooks.service;

import com.webapp.usedtextbooks.model.Users;
import com.webapp.usedtextbooks.repository.UsersRepository;
import com.webapp.usedtextbooks.service.serviceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UsersRepository usersRepository;

    @Transactional
    @Override
    public String addUser(Users users){
        if(usersRepository.findByEmailAddress(users.getEmailAddress().toLowerCase())== null){
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            users.setCreateTime(currentTimestamp);

            String emailToLowerCase = users.getEmailAddress().toLowerCase();
            users.setEmailAddress(emailToLowerCase);

            usersRepository.save(users);

            return "User added with emailId" + users.getEmailAddress().toLowerCase();
        }
        else

           return "The User with email " + users.getEmailAddress() + " already exists.";
        }
}

