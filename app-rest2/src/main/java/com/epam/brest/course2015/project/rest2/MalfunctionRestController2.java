package com.epam.brest.course2015.project.rest2;

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
    @RequestMapping(value = "/malfunction/{id}/{costRepair}/{costService}/{additionalExpenses}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addCosts(@PathVariable(value = "id") Integer id,
            @PathVariable(value = "costRepair") Integer costRepair,
                         @PathVariable(value = "costService") Integer costService,
                         @PathVariable(value = "additionalExpenses") Integer additionalExpenses) {
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

    @RequestMapping(value = "/malfunction/getCost/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Integer getCostMalfunctionById(@PathVariable(value = "id") Integer id) {
        return malfunctionService.getCostForMalfunctionById(id);
    }

    @RequestMapping(value = "/malfunction/getCostApplication/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Integer getCostMalfunctionsByApplicationId(@PathVariable(value = "id") Integer id) {
        return malfunctionService.getCostsForMalfunctionByApplicationId(id);
    }

}