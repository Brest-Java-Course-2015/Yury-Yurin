/*
package com.epam.brest.course2015.project.rest;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.Producer;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
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
public class MockApplicationRestControllerTest extends CamelTestSupport {
    @Autowired
    private ApplicationService applicationService;

    @Override
    public boolean isDumpRouteCoverage() {
        return true;
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
              rest("/application").get().outType(List.class).to("bean:applicationService?method=getAllApplications");
            }
        };
    }

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndPoint;

    @Produce(uri = "direct:start")
    private ProducerTemplate producerTemplate;

    private Application application = new Application(null, new Date(), new Date());

    private MockMvc mockMvc;

    @Test
    public void firstTest() throws Exception {
        List<Application> applicationList = new ArrayList<Application>();
        applicationList.add(application);
        resultEndPoint.expectedBodiesReceived(applicationList);
        producerTemplate.sendBody(applicationList);
        resultEndPoint.assertIsSatisfied();
    }
  */
/*  @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(applicationRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown() {
        verify(applicationService);
        reset(applicationService);
    }*//*

*/
/*
    @Test
    public void TestAddApplication() throws Exception {
        expect(applicationService.addApplication(anyObject(Application.class))).andReturn(1);
        replay(applicationService);
        String newApplication = new ObjectMapper().writeValueAsString(application);
        mockMvc.perform(post("/application").accept(MediaType.APPLICATION_JSON)
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
        mockMvc.perform(delete("/application/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void TestGetApplicationById() throws Exception {
        application.setApplicationId(1);
        expect(applicationService.getApplicationById(1)).andReturn(application);
        expectLastCall();
        replay(applicationService);
        mockMvc.perform(get("/application/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

   *//*
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
*/
