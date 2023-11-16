package me.tin.EAS.service;

import me.tin.EAS.model.Posts;
import me.tin.EAS.model.User;
import me.tin.EAS.model.Users;

public interface UserService {
    User login(String username, String password);

    void deleteUser(String id);

    Users getAllUsers();

    User register(String username, String password, int authLevel);
}
