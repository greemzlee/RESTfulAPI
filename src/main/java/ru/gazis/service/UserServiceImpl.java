package ru.gazis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.gazis.bean.User;
import ru.gazis.dao.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User getUser(Integer userId) {
        User user = userDao.getUser(userId);
        return user;
    }

    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
