package com.citi.spring.test;


import com.citi.spring.web.dao.HandoverDao;
import com.citi.spring.web.dao.data.CurrentlyWith;
import com.citi.spring.web.dao.data.Environment;
import com.citi.spring.web.dao.data.Status;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.service.HandoverService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml",
        "classpath:dao-context.xml",
        "classpath:datasource.xml",
        "classpath:security-context.xml",
        "classpath:service-context.xml",
        "classpath:excel-view.xml"
})
@WebAppConfiguration
public class HandoverControllerTests {
    private Timestamp current = new Timestamp(System.currentTimeMillis());

    private MockMvc mockMvc;

    @Mock
    private HandoverService handoverService;

    @Mock
    private HandoverDao handoverDao;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Handover handover2 ;
    private Handover handover1;
    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
       // Mockito.reset(handoverService);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

         handover1 = new Handover(current, "Rahul", "Rahul email subject",
                "C167433-123", "Rahul in test comments",
                "", Status.HIGH, CurrentlyWith.AMC, Environment.PROD);
        handover1.setId(1);

         handover2 = new Handover(current, "Gaurav", "Gaurav email subject",
                "www.google.com", "Gaurav in test comments",
                "", Status.HIGH, CurrentlyWith.KYC, Environment.UAT);
        handover2.setId(2);

    }

    @Test
    public void showHandoverTest() throws Exception {

        try {
            when(handoverService.getCurrentHandover()).thenReturn(Arrays.asList(handover1, handover2));
        } catch (NullPointerException ex){
            System.out.println(ex.getCause() + "  ..............   " + ex.getClass() );
            ex.printStackTrace();
        }
        mockMvc.perform(get("/handover")).andExpect(status().isOk());
    }

}
