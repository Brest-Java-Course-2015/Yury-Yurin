package com.epam.brest.course2015.project.rest;

import com.epam.brest.course2015.project.core.Costs;
import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.service.MalfunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MalfunctionRestController {

    @Autowired
    private MalfunctionService malfunctionService;

    @RequestMapping(value = "/malfunctions/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Malfunction> getMalfunctions(@PathVariable(value = "id") Integer id) {
        return malfunctionService.getAllMalfunctionsByIdApplication(id);
    }

    @RequestMapping(value = "/malfunctions", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Malfunction> getAllMalfunctions() {
        return malfunctionService.getAllMalfunctions();
    }


    @RequestMapping(value = "/malfunction/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Malfunction getMalfunctionById(@PathVariable(value = "id") Integer id) {
        return malfunctionService.getMalfunctionById(id);
    }

    @RequestMapping(value = "/malfunction", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Integer addMalfunction(@RequestBody Malfunction malfunction) {
        return malfunctionService.addMalfunction(malfunction);
    }
    @RequestMapping(value = "/malfunction/setCosts", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void addCosts(@RequestParam(value = "id") Integer id,
            @RequestParam(value = "costRepair") Integer costRepair,
                         @RequestParam(value = "costService") Integer costService,
                         @RequestParam(value = "additionalExpenses") Integer additionalExpenses) {
        malfunctionService.addCostsToMalfunction(id,costRepair,costService,additionalExpenses);
    }

    @RequestMapping(value = "/malfunction/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateMalfunction(@RequestBody Malfunction malfunction) {
        malfunctionService.updateMalfunction(malfunction);
    }

    @RequestMapping(value = "/malfunction/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteMalfunction(@PathVariable(value = "id") Integer id) {
        malfunctionService.deleteMalfunction(id);
    }

    @RequestMapping(value = "/malfunction/getCostsMalfunctions", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Costs> getCostsMalfunctions() {
        return malfunctionService.getMalfunctionsCosts();
    }

    @RequestMapping(value = "/malfunction/getCostsApplications", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Costs> getApplicationsCosts() {
        return malfunctionService.getApplicationsCosts();
    }

}
