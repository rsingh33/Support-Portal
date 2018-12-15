package tests;

import com.citi.spring.web.dao.entity.Offer;
import com.citi.spring.web.dao.OffersDao;
import com.citi.spring.web.dao.entity.User;
import com.citi.spring.web.dao.UsersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OffersDaoTests {


    @Autowired
    private OffersDao offersDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private DataSource dataSource;

    private User user1 = new User("rsingh33", "Rahul", "abc123456", "r@singh.com", true, "ROLE_admin");
    private User user2 = new User("gsingh33", "Rahul", "abc123456", "r@singh.com", true, "ROLE_user");
    private User user3 = new User("isingh33", "Rahul", "abc123456", "r@singh.com", true, "ROLE_user");
    private User user4 = new User("ssingh33", "Rahul", "abc123456", "r@singh.com", false, "ROLE_user");

    private Offer offer1 = new Offer(user1, "test for offer 1");
    private Offer offer2 = new Offer(user1, "test for offer 1");
    private Offer offer3 = new Offer(user2, "test for offer 1");
    private Offer offer4 = new Offer(user3, "test for offer 1");
    private Offer offer5 = new Offer(user3, "test for offer 1");
    private Offer offer6 = new Offer(user3, "test for offer 1");
    private Offer offer7 = new Offer(user4, "test for offer 1");

    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from offers");
        jdbc.execute("delete from users");

    }

    @Test
    public void testCreate() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user4);
        usersDao.create(user3);

        offersDao.saveOrUpdate(offer1);
        List<Offer> offers1 = offersDao.getOffers();
        assertEquals("should be one offer", 1, offers1.size());
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);
        offersDao.saveOrUpdate(offer3);

        List<Offer> offers = offersDao.getOffers();
        assertEquals("should be 6 offers for enabled users", 6, offers.size());

    }

    @Test
    public void testGetOffersByUsername() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user4);
        usersDao.create(user3);

        offersDao.saveOrUpdate(offer1);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);
        offersDao.saveOrUpdate(offer3);


        List<Offer> offers1 = offersDao.getOffers(user3.getusername());
        assertEquals("should be three offers for this user ", 3, offers1.size());
        List<Offer> offers2 = offersDao.getOffers("fake_user");
        assertEquals("should be zero offers for this user ", 0, offers2.size());
        List<Offer> offers3 = offersDao.getOffers(user2.getusername());
        assertEquals("should be one offer for this user ", 1, offers3.size());
    }

    @Test
    public void testUpdate() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user4);
        usersDao.create(user3);

        offersDao.saveOrUpdate(offer1);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);
        offersDao.saveOrUpdate(offer3);

        offer3.setText("This offer has updated text");
        offersDao.saveOrUpdate(offer3);

        Offer retrieved = offersDao.getOffer(offer3.getId());
        assertEquals("Retrived offer should be updated. ", offer3, retrieved);
        System.out.println(retrieved.getText());

    }

    @Test
    public void testDelete() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user4);
        usersDao.create(user3);

        offersDao.saveOrUpdate(offer1);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);
        offersDao.saveOrUpdate(offer3);


        Offer retrieved = offersDao.getOffer(offer2.getId());
        assertNotNull("offer with id: " + retrieved.getId() + " should not be null", retrieved);


        offersDao.delete(offer2.getId());
        assertFalse("should be false as offer is already deleted", offersDao.delete(offer2.getId()));


    }

    @Test
    public void testOfferById() {
        usersDao.create(user1);
        usersDao.create(user2);
        usersDao.create(user4);
        usersDao.create(user3);

        offersDao.saveOrUpdate(offer1);
        offersDao.saveOrUpdate(offer2);
        offersDao.saveOrUpdate(offer4);
        offersDao.saveOrUpdate(offer5);
        offersDao.saveOrUpdate(offer6);
        offersDao.saveOrUpdate(offer7);
        offersDao.saveOrUpdate(offer3);

        Offer retrieved = offersDao.getOffer(offer1.getId());
        Offer retrieved2 = offersDao.getOffer(offer7.getId());

        assertEquals("offers should match: ", offer1, retrieved);
        assertNotNull("should not be null", offersDao.getOffer(offer2.getId()));
        assertNull("should be null as user 4 is not enabled ", retrieved2);
    }

}
