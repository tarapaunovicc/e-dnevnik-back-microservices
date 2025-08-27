package fon.e_dnevnik.userservice.rest;

import fon.e_dnevnik.userservice.dto.SubjectDTO;
import fon.e_dnevnik.userservice.dto.TeacherDTO;
import fon.e_dnevnik.userservice.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private TeacherService teacherImplementation;

    @Autowired
    public TeacherController(TeacherService teacherImplementation) {
        this.teacherImplementation = teacherImplementation;
    }

    @GetMapping("{id}")
    public ResponseEntity<TeacherDTO> findById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(teacherImplementation.findById((String)id));
    }

    @GetMapping("/{username}/subject")
    public SubjectDTO getSubject(@PathVariable String username) {
        return teacherImplementation.getSubject(username);
    }
}
