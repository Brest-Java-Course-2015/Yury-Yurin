package com.epam.brest.course2015.project.rest;

import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.service.MalfunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MalfunctionRestController {

    @Autowired
    private MalfunctionService malfunctionService;

    @RequestMapping(value = "/malfunctions/{id}", method = RequestMethod.GET)
    public @ResponseBody List<Malfunction> getMalfunctions(@PathVariable(value = "id") Integer id) {
        return malfunctionService.getAllMalfunctionsByIdApplication(id);
    }
}
