package me.tin.EAS.service;

import me.tin.EAS.model.User;
import me.tin.EAS.model.Users;
import me.tin.EAS.util.Utility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User login(String username, String password) {
        Users users = new Users();

        Users fetchedUsers = Utility.loadFromJsonFile("users.json", Users.class);
        if(fetchedUsers != null && fetchedUsers.getUsers() != null)
            users.setUsers(fetchedUsers.getUsers());

        if(users.getUsers() != null && !users.getUsers().isEmpty()) {
            for (User user : users.getUsers()) {
                if (user.getUsername().equals(username))
                    if(user.getPassword().equals(password))
                        return user;
            }
        }

        return null;
    }

    @Override
    public User register(String username, String password, int authLevel) {
        Users users = new Users();

        Users fetchedUsers = Utility.loadFromJsonFile("users.json", Users.class);
        if(fetchedUsers != null && fetchedUsers.getUsers() != null)
            users.setUsers(fetchedUsers.getUsers());

        if(users.getUsers() != null && !users.getUsers().isEmpty()) {
            for (User user : users.getUsers()) {
                if (user.getUsername().equals(username))
                    return null;
            }
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAuthLevel(authLevel);
        user.setId(Utility.generateRandomString());

        if(users.getUsers() != null) {
            users.getUsers().add(user);
        } else {
            users.setUsers(List.of(user));
        }

        Utility.saveToJsonFile(users, "users.json");

        return user;
    }
}
