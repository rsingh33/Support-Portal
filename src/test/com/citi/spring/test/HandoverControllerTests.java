package com.citi.spring.test;


import com.citi.spring.web.dao.data.CurrentlyWith;
import com.citi.spring.web.dao.data.Environment;
import com.citi.spring.web.dao.data.Status;
import com.citi.spring.web.dao.entity.Handover;
import com.citi.spring.web.service.HandoverService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =  { "classpath:testContext.xml",
        "classpath:support-portal-servlet.xml",
        "classpath:dao-context.xml",
        "classpath:datasource.xml",
        "classpath:security-context.xml",
        "classpath:service-context.xml",
        "classpath:excel-view.xml"})
@WebAppConfiguration
public class HandoverControllerTests {
    private Timestamp current = new Timestamp(System.currentTimeMillis());

    private MockMvc mockMvc;

    @Autowired
    private HandoverService handoverService;


    @Test
    public void showHandoverTest() throws Exception {
        Handover handover1 = new Handover(current, "Rahul", "Rahul email subject",
                "C167433-123", "Rahul in test comments",
                "", Status.HIGH, CurrentlyWith.AMC, Environment.PROD);
        handover1.setId(1);

        Handover handover2 = new Handover(current, "Gaurav", "Gaurav email subject",
                "www.google.com", "Gaurav in test comments",
                "", Status.HIGH, CurrentlyWith.KYC, Environment.UAT);
        handover2.setId(2);

        when(handoverService.getCurrentHandover()).thenReturn(Arrays.asList(handover1, handover2));

        mockMvc.perform(get("/handover"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML));
    }
}
