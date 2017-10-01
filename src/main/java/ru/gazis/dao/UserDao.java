package ru.gazis.dao;

import ru.gazis.bean.User;

public interface UserDao {
    public User getUserByUserName(String userName);
    public User getUserById(Integer userId);
    public int updateUser(User user);
}
