package ru.gazis.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gazis.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User getUserByUserName(String userName) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE userName = ?",
                    new Object[]{userName}, new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new User(
                                    resultSet.getInt("userId"),
                                    resultSet.getString("userName"),
                                    resultSet.getString("first"),
                                    resultSet.getString("last")
                            );
                        }
                    });
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    public User getUserById(Integer userId) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE userId = ?",
                    new Object[]{userId}, new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new User(
                                    resultSet.getInt("userId"),
                                    resultSet.getString("userName"),
                                    resultSet.getString("first"),
                                    resultSet.getString("last")
                            );
                        }
                    });
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    public int updateUser(User user) {
        int count = jdbcTemplate.update(
                "UPDATE user set last = ? where userId = ?",
                new Object[] {user.getLastName(), user.getUserId()});
        return count;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
