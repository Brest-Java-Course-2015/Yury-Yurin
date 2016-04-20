package com.epam.brest.course2015.project.rest;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.core.Malfunction;
import io.swagger.util.Json;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Route;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.junit4.TestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:/camel.xml")
public class MockMalfunctionRestControllerTest extends CamelTestSupport {

   RestTemplate restTemplate = new RestTemplate();

    @Test
    public void checkContext() throws InterruptedException {
        Thread.sleep(3600000L);
    }

    @DirtiesContext
    @Test
    public void getApplicationsTest() {
        List<Application> list = restTemplate.getForObject("http://localhost:8282/applications",List.class);
        assertTrue(list.size() == 2);
    }

    @DirtiesContext
    @Test
    public void getMalfunctionsByIdApplicationTest() {
        List<Malfunction> list = restTemplate.getForObject("http://localhost:8282/malfunctions?id=2",List.class);
        assertTrue(list.size()==2);
    }

    @DirtiesContext
    @Test
    public void deleteMalfunction() {
        restTemplate.delete("http://localhost:8282/malfunction/delete?id=1");
       // restTemplate.postForObject("http://localhost:8282/malfunction/delete?id=1", null, Integer.class);*/
        List<Malfunction> list = restTemplate.getForObject("http://localhost:8282/malfunctions?id=1",List.class);
        assertTrue(list.size()==1);
    }

    @DirtiesContext
    @Test
    public void addApplication() {
        Application application = new Application(3,new Date(), new Date());
        int id = restTemplate.postForObject("http://localhost:8282/application", application,Integer.class);
        List<Application> list = restTemplate.getForObject("http://localhost:8282/applications",List.class);
        assertTrue(list.size() == 2);
    }

}
/*
    @Autowired
    private MalfunctionService malfunctionService;

    @Resource
    private MalfunctionRestController malfunctionRestController;

    private Malfunction malfunction = new Malfunction(null,"name","auto","description",1);

    private MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(malfunctionRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown() {
        verify(malfunctionService);
        reset(malfunctionService);
    }

    @Test
    public void TestAddMalfunction() throws Exception {
        expect(malfunctionService.addMalfunction(anyObject(Malfunction.class))).andReturn(1);
        replay(malfunctionService);
        String newMalfunction = new ObjectMapper().writeValueAsString(malfunction);
        mockMvc.perform(post("/malfunction").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newMalfunction))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void TestUpdateMalfunction() throws Exception {
        //malfunction.setMalfunctionId(1);
        malfunctionService.updateMalfunction(anyObject(Malfunction.class));
        expectLastCall();
       String newMalfunction = new ObjectMapper().writeValueAsString(malfunction);
        replay(malfunctionService);
        mockMvc.perform(put("/malfunction/update").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(newMalfunction))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(""));
    }


    @Test
    public void TestDeleteMalfunction() throws Exception {
        malfunctionService.deleteMalfunction(1);
        expectLastCall();
        replay(malfunctionService);
        mockMvc.perform(delete("/malfunction/delete/1"))
                .andDo(print())  MockEndpoint mockEndpoint = getMockEndpoint("mock:applicationService?method=getAllApplications");
        mockEndpoint.expectedBodiesReceived("HelloWorld");
        template.sendBody("mock:applicationService?method=getAllApplications","HelloWorld");
        assertMockEndpointsSatisfied();
    }
/*
    @Autowired
    private MalfunctionService malfunctionService;

    @Resource
    private MalfunctionRestController malfunctionRestController;

    private Malfunction malfunction = new Malfunction(null,"name","auto","description",1);

    private MockMvc mockMvc;


    @Before
    public void setUp() {
                .andExpect(status().isOk());

    }

    @Test
    public void TestGetMalfunctionById() throws Exception {
        malfunction.setMalfunctionId(1);
        expect(malfunctionService.getMalfunctionById(1)).andReturn(malfunction);
        expectLastCall();
        replay(malfunctionService);
        mockMvc.perform(get("/malfunction/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void TestGetMalfunctionsByApplicationId() throws Exception {
        malfunction.setApplicationId(1);
        List<Malfunction> malfunctionList = new ArrayList<Malfunction>();
        malfunctionList.add(malfunction);
        expect(malfunctionService.getAllMalfunctionsByIdApplication(1)).andReturn(malfunctionList);
        replay(malfunctionService);
        mockMvc.perform(get("/malfunctions/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void TestGetAllMalfunctions() throws Exception {
        malfunction.setApplicationId(1);
        List<Malfunction> malfunctionList = new ArrayList<Malfunction>();
        malfunctionList.add(malfunction);
        expect(malfunctionService.getAllMalfunctions()).andReturn(malfunctionList);
        replay(malfunctionService);
        mockMvc.perform(get("/malfunctions").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void TestAddCosts() throws Exception {
        malfunctionService.addCostsToMalfunction(1, 1000, 2000, 3000);
        expectLastCall();
        replay(malfunctionService);
        mockMvc.perform(put("/malfunction/setCosts?id=1&costRepair=1000&costService=2000&additionalExpenses=3000"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testGetCostMalfunction() throws Exception {
        List<Costs> costs = new ArrayList<Costs>();
        costs.add(new Costs(1,12000));
        expect(malfunctionService.getMalfunctionsCosts()).andReturn(costs);
        replay(malfunctionService);
        mockMvc.perform(get("/malfunction/getCostsMalfunctions"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testGetCostForMalfunctionByApplicationId() throws Exception {
        List<Costs> costs = new ArrayList<Costs>();
        costs.add(new Costs(1,12000));
        expect(malfunctionService.getApplicationsCosts()).andReturn(costs);
        replay(malfunctionService);
        mockMvc.perform(get("/malfunction/getCostsApplications"))
                .andDo(print())
                .andExpect(status().isOk());
    }*/