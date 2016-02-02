package com.epam.brest.course2015.project.web.app;

import com.epam.brest.course2015.project.core.Application;
import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.service.ApplicationService;
import com.epam.brest.course2015.project.service.MalfunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class SimpleAutoServiceRestController {


    @Autowired
    private MalfunctionService malfunctionService;

    @Autowired
    private ApplicationService applicationService;
    @RequestMapping("/applicationSubmit")
    public List<Malfunction> addApplicationSubmit(@RequestBody Malfunction malfunction) {
        if(malfunction.getApplicationId()==null) {
            Application application = new Application(null, new Date(), new Date());
            Integer id = applicationService.addApplication(application);
            malfunction.setApplicationId(id);
        }
        malfunctionService.addMalfunction(malfunction);
        applicationService.updateApplication(malfunction.getApplicationId(), new Date());
        return malfunctionService.getAllMalfunctionsByIdApplication(malfunction.getApplicationId());
    }
}
