package fon.e_dnevnik.userservice.rest;

import fon.e_dnevnik.userservice.dto.StudentDTO;
import fon.e_dnevnik.userservice.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentImplementation;

    @Autowired
    public StudentController(StudentService studentImplementation) {
        this.studentImplementation = studentImplementation;
    }

    @GetMapping("{username}")
    public ResponseEntity<StudentDTO> findById(@PathVariable String username) throws Exception {
        return ResponseEntity.ok().body(studentImplementation.findById((String)username));
    }

    @GetMapping("/class/{classid}")
    public List<StudentDTO> findByStudentClassClassId(@PathVariable int classid){
        System.out.println("Uslo je u kontrolera");
        return studentImplementation.findByStudentClassClassId(classid);
    }
}