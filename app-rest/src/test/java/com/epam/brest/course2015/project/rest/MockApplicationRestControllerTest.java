package com.epam.brest.course2015.project.rest;

import com.epam.brest.course2015.project.core.Application;
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
@ContextConfiguration(value = "classpath*:mock-test-spring-rest.xml")
public class MockApplicationRestControllerTest {
    @Autowired
    private ApplicationService applicationService;

    @Resource
    private ApplicationRestController applicationRestController;

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
        mockMvc.perform(put("/application").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newApplication))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void TestUpdateApplication() throws Exception {
        applicationService.updateApplication(anyObject(Application.class));
        expectLastCall();
        String newApplication = new ObjectMapper().writeValueAsString(application);
        replay(applicationService);
        mockMvc.perform(post("/application/update").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newApplication))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(""));
    }


    @Test
    public void TestDeleteMalfunction() throws Exception {
        applicationService.deleteApplication(1);
        expectLastCall();
        replay(applicationService);
        mockMvc.perform(delete("/application/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void TestGetMalfunctionById() throws Exception {
        application.setApplicationId(1);
        expect(applicationService.getApplicationById(1)).andReturn(application);
        expectLastCall();
        replay(applicationService);
        mockMvc.perform(get("/application/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void getAllApplications() throws Exception {
        List<Application> applicationList = new ArrayList<Application>();
        applicationList.add(application);
        expect(applicationService.getAllApplications()).andReturn(applicationList);
        replay(applicationService);
        mockMvc.perform(get("/applications").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

}
