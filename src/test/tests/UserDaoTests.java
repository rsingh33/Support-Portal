package tests;

import com.citi.spring.web.dao.data.Roles;
import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.dao.UsersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {"/WEB-INF/dao-context.xml",
        "classpath:WEB-INF/security-context.xml",
        "classpath:/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

    @Qualifier("usersDao")
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource dataSource;

    private User user1 = new User("rsingh33", "Rahul", "abc123456", "r@singh.com", true,"ROLE_user");
    private User user2 = new User("gsingh33", "Rahul", "abc123456", "r@singh.com", true,"ROLE_user");
    private User user3 = new User("isingh33", "Rahul", "abc123456", "r@singh.com", true,"ROLE_user");
    private User user4 = new User("ssingh33", "Rahul", "abc123456", "r@singh.com", true,"ROLE_user");

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);


        jdbc.execute("delete from users");

    }

    @Test
    public void testCreateRetrieve() {
        usersDao.create(user1);
        List<User> users = usersDao.getAllUsers();
        assertEquals("one user should have been retrived", 1, users.size());
        assertEquals("Iserted should match retrieved", user1, users.get(0));

        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);

        List<User> users1 = usersDao.getAllUsers();
        assertEquals("should be 4 retrieved users", 4, users1.size());


    }

    @Test
    public void testExists() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user3);
        usersDao.create(user4);


        assertTrue("User should exist.", usersDao.exists(user1.getusername()));
        assertTrue("User should exist.", usersDao.exists(user2.getusername()));
        assertTrue("User should exist.", usersDao.exists(user3.getusername()));
        assertTrue("User should exist.", usersDao.exists(user4.getusername()));
        assertFalse("User should not exist.", usersDao.exists("fake_user"));
    }


}
