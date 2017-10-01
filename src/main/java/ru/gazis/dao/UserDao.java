package ru.gazis.dao;

import ru.gazis.bean.User;

public interface UserDao {
    public User getUser(Integer userId);
    public int updateUser(User user);
}
