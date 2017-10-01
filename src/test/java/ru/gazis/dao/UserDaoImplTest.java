package ru.gazis.dao;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gazis.bean.User;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/app-context.xml")
@WebAppConfiguration
public class UserDaoImplTest extends TestCase {

    @Autowired
    UserDaoImpl dao;

//    public UserDaoImplTest() {
//        dao = new UserDaoImpl();
//        dao.setDataSource(null);
//    }

    @Test
    public void testSetDataSource() throws Exception {
        DataSource source = dao.getJdbcTemplate().getDataSource();
        System.out.println(source);
        BasicDataSource newSource = new BasicDataSource();
        dao.setDataSource(newSource);
        DataSource changedSource = dao.getJdbcTemplate().getDataSource();
        assertEquals(changedSource, newSource);
        dao.setDataSource(source);
    }

    @Test
    public void testGetUser() throws Exception {
        User user = dao.getUser(1);
        assertNotNull(user);
    }

    @Test
    public void testGetUserNotFound() throws Exception {
        User user = dao.getUser(1000);
        assertNull(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = dao.getUser(1);
        String oldLast = user.getLastName();
        String newLast = oldLast + "1";
        user.setLast(newLast);
        dao.updateUser(user);
        User changedUser = dao.getUser(1);
        assertEquals(changedUser.getLastName(), newLast);
    }

}