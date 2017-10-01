package ru.gazis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.gazis.bean.User;
import ru.gazis.dao.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User getUserByUserName(String userName) {
        User user = userDao.getUserByUserName(userName);
        return user;
    }

    public User getUserById(Integer userId) {
        User user = userDao.getUserById(userId);
        return user;
    }

    public int updateUser(User user) {
        return userDao.updateUser(user);
    }
}
