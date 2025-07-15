package fon.e_dnevnik.classservice.rest;


import fon.e_dnevnik.classservice.dto.TeachersClassesDTO;
import fon.e_dnevnik.classservice.service.TeachersClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachersclasses")
public class TeachersClassesController {
    private TeachersClassesService teachersClassesImplementation;

    @Autowired
    public TeachersClassesController(TeachersClassesService teachersClassesImplementation) {
        this.teachersClassesImplementation = teachersClassesImplementation;
    }
    @GetMapping("{username}")
    public List<TeachersClassesDTO> findByTeacherUsername(@PathVariable String username){
        return teachersClassesImplementation.findByTeacherUsername(username);
    }
}
