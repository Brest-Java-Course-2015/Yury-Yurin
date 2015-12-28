package com.epam.brest.course2015.project.rest2;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.rest2.ApplicationRestController2;
import com.epam.brest.course2015.project.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:mock-test-spring-rest2.xml")
public class MockApplicationRestControllerTest {
    @Autowired
    private ApplicationService applicationService;

    @Resource
    private ApplicationRestController2 applicationRestController;

    private Application application = new Application(null, new Date(), new Date());

    private MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(applicationRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown() {
        verify(applicationService);
        reset(applicationService);
    }

    @Test
    public void TestAddApplication() throws Exception {
        expect(applicationService.addApplication(anyObject(Application.class))).andReturn(1);
        replay(applicationService);
        String newApplication = new ObjectMapper().writeValueAsString(application);
        mockMvc.perform(post("/application2").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newApplication))
                .andDo(print())
                .andExpect(status().isCreated());

    }


    @Test
    public void TestDeleteApplication() throws Exception {
        applicationService.deleteApplication(1);
        expectLastCall();
        replay(applicationService);
        mockMvc.perform(delete("/application2/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void TestGetApplicationById() throws Exception {
        application.setApplicationId(1);
        expect(applicationService.getApplicationById(1)).andReturn(application);
        expectLastCall();
        replay(applicationService);
        mockMvc.perform(get("/application2/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void getAllApplications() throws Exception {
        List<Application> applicationList = new ArrayList<Application>();
        applicationList.add(application);
        expect(applicationService.getAllApplications()).andReturn(applicationList);
        replay(applicationService);
        mockMvc.perform(get("/applications2").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

}
