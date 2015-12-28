package com.epam.brest.course2015.project.rest2;

import com.epam.brest.course2015.project.core.ApplicationCosts;
import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.service.MalfunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MalfunctionRestController2 {

    @Autowired
    private MalfunctionService malfunctionService;

    @RequestMapping(value = "/malfunctions2/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Malfunction> getMalfunctions(@PathVariable(value = "id") Integer id) {
        return malfunctionService.getAllMalfunctionsByIdApplication(id);
    }

    @RequestMapping(value = "/malfunctions2", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Malfunction> getAllMalfunctions() {
        return malfunctionService.getAllMalfunctions();
    }


    @RequestMapping(value = "/malfunction2/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Malfunction getMalfunctionById(@PathVariable(value = "id") Integer id) {
        return malfunctionService.getMalfunctionById(id);
    }

    @RequestMapping(value = "/malfunction2", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addMalfunction(@RequestBody Malfunction malfunction) {
        return malfunctionService.addMalfunction(malfunction);
    }
    @RequestMapping(value = "/malfunction2/setCosts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addCosts(@RequestParam(value = "id") Integer id,
            @RequestParam(value = "costRepair") Integer costRepair,
                         @RequestParam(value = "costService") Integer costService,
                         @RequestParam(value = "additionalExpenses") Integer additionalExpenses) {
        malfunctionService.addCostsToMalfunction(id,costRepair,costService,additionalExpenses);
    }

    @RequestMapping(value = "/malfunction2/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateMalfunction(@RequestBody Malfunction malfunction) {
        malfunctionService.updateMalfunction(malfunction);
    }

    @RequestMapping(value = "/malfunction2/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteMalfunction(@PathVariable(value = "id") Integer id) {
        malfunctionService.deleteMalfunction(id);
    }

    @RequestMapping(value = "/malfunction2/getCostsMalfunctions", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<ApplicationCosts> getCostsMalfunctions() {
        return malfunctionService.getMalfunctionsCosts();
    }

    @RequestMapping(value = "/malfunction2/getCostsApplications", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<ApplicationCosts> getApplicationsCosts() {
        return malfunctionService.getApplicationsCosts();
    }

}
