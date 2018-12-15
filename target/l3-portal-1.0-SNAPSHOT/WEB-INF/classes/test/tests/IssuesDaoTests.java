package tests;


import com.citi.spring.web.dao.IssuesDao;
import com.citi.spring.web.dao.data.CurrentlyWith;
import com.citi.spring.web.dao.data.Status;
import com.citi.spring.web.dao.entity.Issue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Timestamp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {"/WEB-INF/dao-context.xml",
        "classpath:WEB-INF/security-context.xml",
        "classpath:/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class IssuesDaoTests {

    Timestamp current = new Timestamp(System.currentTimeMillis());

    @Autowired
    private IssuesDao issuesDao;

    @Autowired
    private DataSource dataSource;
    private Issue issue1 = new Issue("Rahul issue description", "Rahul solution", "www.google.com", "Rahul workaround", current, CurrentlyWith.AMC);

    private Issue issue2 = new Issue("Anoosha issue description", "Anoosha solution", "www.google.com", "Anoosha workaround", current, CurrentlyWith.DMC);

    private Issue issue3 = new Issue("Anoosha issue description", "Pria solution", "www.google.com", "Pria workaround", current, CurrentlyWith.KYC);

    private Issue issue4 = new Issue("Anoosha Wayne issue description", "John Wayne solution", "www.google.com", "John Wayne workaround", current, CurrentlyWith.REPORTING);


    @Before
    public void init() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        jdbc.execute("delete from issues");
    }

    @Test
    public void testCreate() {

        issuesDao.saveOrUpdateIssue(issue1);
        issuesDao.saveOrUpdateIssue(issue2);
        issuesDao.saveOrUpdateIssue(issue3);
        issuesDao.saveOrUpdateIssue(issue4);
        assertEquals("there should be 4 enteries in issues", 4, issuesDao.getIssue().size());
    }

    @Test
    public void testUpdate() {
        issuesDao.saveOrUpdateIssue(issue1);

        System.out.println("Source of issue 1 should be AMC =  " + issue1.getSourceSystem());

        issue1.setSourceSystem(CurrentlyWith.CONFIG);
        issuesDao.saveOrUpdateIssue(issue1);

       assertEquals("Source should be same", CurrentlyWith.CONFIG, issuesDao.getIssue(issue1.getId()).getSourceSystem());
        System.out.println("Source of issue 1 be Config =  " + issue1.getSourceSystem());
    }

    @Test
    public void testGetByKeyword() {

        issuesDao.saveOrUpdateIssue(issue2);
        issuesDao.saveOrUpdateIssue(issue3);
        issuesDao.saveOrUpdateIssue(issue4);
        issuesDao.saveOrUpdateIssue(issue1);

       assertEquals("Should only retrieve 3 entries containg Aoosha in issue description", 3, issuesDao.getIssue("Anoosha").size());


    }

    @Test
    public void testDelete() {
        issuesDao.saveOrUpdateIssue(issue2);
        issuesDao.saveOrUpdateIssue(issue3);
        issuesDao.saveOrUpdateIssue(issue4);
        issuesDao.saveOrUpdateIssue(issue1);


       assertEquals("there should be 4 enteries in Handpver", 4, issuesDao.getIssue().size());

       issuesDao.deleteIssue(issue1.getId());
       assertEquals("there should be 3 enteries in Handpver", 3, issuesDao.getIssue().size());


       assertFalse("the issue is already deleted",   issuesDao.deleteIssue(issue1.getId()));
    }

}
