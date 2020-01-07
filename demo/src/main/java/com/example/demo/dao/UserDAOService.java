package com.example.demo.dao;

import com.example.demo.pojos.User;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService {
    private static List<User> userList = new ArrayList<>();
    private static int userCount=3;

    static{
        userList.add(new User(1,"Aishwarya", new Date()));
        userList.add(new User(2,"Ashok", new Date()));
        userList.add(new User(3,"Anjali", new Date()));
    }

    public List<User> findAll(){
        return userList;
    }

    public User saveUser(User user){
        if(user.getId()==0){
            user.setId(++userCount);
        }
      userList.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User u : userList) {
            if (u.getId() == id)
                return u;
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if(user.getId()==id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
