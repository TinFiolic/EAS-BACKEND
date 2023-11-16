package me.tin.EAS.service;

import me.tin.EAS.model.User;

public interface UserService {
    User login(String username, String password);
    User register(String username, String password, int authLevel);
}
