package ru.gazis.service;

import com.sun.istack.internal.NotNull;
import ru.gazis.bean.User;

public interface UserService {
    public User getUser(Integer userId);
    public int updateUser( User user);
}
