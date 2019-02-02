package tests;


import com.citi.spring.web.dao.HandoverDao;
import com.citi.spring.web.dao.data.CurrentlyWith;
import com.citi.spring.web.dao.data.Environment;
import com.citi.spring.web.dao.data.Status;
import com.citi.spring.web.dao.entity.Handover;
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
import java.sql.Timestamp;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {"/WEB-INF/dao-context.xml",
        "classpath:WEB-INF/security-context.xml",
        "classpath:/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MonitorDaoTests {

    Timestamp current = new Timestamp(System.currentTimeMillis());
    @Qualifier("handoverDao")
    @Autowired
    private HandoverDao handoverDao;

    @Autowired
    private DataSource dataSource;
    private Handover handover1 = new Handover(current, "Rahul", "Rahul email subject",
            "www.google.com", "Rahul in test comments",
            "", Status.HIGH, CurrentlyWith.AMC, Environment.PROD);

    private Handover handover2 = new Handover(current, "Gaurav", "Gaurav email subject",
            "www.google.com", "Gaurav in test comments",
            "", Status.HIGH, CurrentlyWith.KYC, Environment.UAT);

    private Handover handover3 = new Handover(current, "Mithun", "Mithun email subject",
            "www.google.com", "Mithun in test comments",
            "", Status.HIGH, CurrentlyWith.PS, Environment.SIT);

    private Handover handover4 = new Handover(current, "Abhi", "Abhi email subject",
            "www.google.com", "Abhi in test comments",
            "", Status.HIGH, CurrentlyWith.DMC, Environment.PROD);


    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from handover");

    }

    @Test
    public  void testCreate(){
        handoverDao.saveOrUpdateHandover(handover1);
        handoverDao.saveOrUpdateHandover(handover2);
        handoverDao.saveOrUpdateHandover(handover3);
        handoverDao.saveOrUpdateHandover(handover4);

        assertEquals("there should be 4 enteries in handover",4,handoverDao.getHandover().size());
    }

    @Test
    public  void testUpdate(){
          handover1.setReportedBy("Priyanka");
          handover1.setStatus(Status.HIGH);

        handoverDao.saveOrUpdateHandover(handover1);

        assertEquals("Name of the reporter should be Priyanka","Priyanka",handoverDao.getHandover(handover1.getId()).getReportedBy());

        System.out.println(handoverDao.getHandover(handover1.getId()).getStatus());
        System.out.println(handoverDao.getHandover(handover1.getId()).getReportedBy());
    }

    @Test
    public  void testGetByReporter(){
        handoverDao.saveOrUpdateHandover(handover1);
        handoverDao.saveOrUpdateHandover(handover2);
        handoverDao.saveOrUpdateHandover(handover3);
        handoverDao.saveOrUpdateHandover(handover4);

        assertEquals("Size of the list should be 1 as there is only one row where reporter is Rahul",1,handoverDao.getHandover("Rahul").size());
        assertEquals("status should be Completed",Status.HIGH,handoverDao.getHandover(handover4.getId()).getStatus());

        System.out.println(handoverDao.getHandover(handover1.getId()).getStatus());
        System.out.println(handoverDao.getHandover(handover1.getId()).getReportedBy());
    }
    @Test
    public  void testDelete(){
        handoverDao.saveOrUpdateHandover(handover1);
        handoverDao.saveOrUpdateHandover(handover2);
        handoverDao.saveOrUpdateHandover(handover3);
        handoverDao.saveOrUpdateHandover(handover4);

        assertEquals("there should be 4 enteries in Handpver",4,handoverDao.getHandover().size());

        handoverDao.deleteHandover(handover1.getId());

        assertEquals("there should be 3 enteries in Handpver",3,handoverDao.getHandover().size());

        handoverDao.saveOrUpdateHandover(handover3);
        handoverDao.saveOrUpdateHandover(handover4);

        handoverDao.deleteHandover(handover4.getId());
        assertEquals("there should be 2 enteries in Handpver",2,handoverDao.getHandover().size());
    }

}
