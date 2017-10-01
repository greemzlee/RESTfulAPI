package ru.gazis.service;

import com.sun.istack.internal.NotNull;
import ru.gazis.bean.User;

public interface UserService {
    public User getUserByUserName(String userName);
    public User getUserById(Integer userId);
    public int updateUser( User user);
}
