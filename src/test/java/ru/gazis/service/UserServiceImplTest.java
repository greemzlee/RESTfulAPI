package ru.gazis.service;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.gazis.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/app-context.xml")
@WebAppConfiguration
public class UserServiceImplTest extends TestCase {

    @Autowired
    UserService service;

    public UserServiceImplTest() {
        service = new UserServiceImpl();
    }

    @Test
    public void testGetUser() throws Exception {
        User user = service.getUserById(1);
        assertNotNull(user);
    }

    @Test
    public void testGetUserNotFound() throws Exception {
        User user = service.getUserById(1);
        assertNotNull(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = service.getUserById(1);
        String oldLast = user.getLastName();
        String newLast = oldLast + "1";
        user.setLast(newLast);
        service.updateUser(user);
        User changedUser = service.getUserById(1);
        assertEquals(changedUser.getLastName(), newLast);
    }

}