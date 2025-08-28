package fon.e_dnevnik.classservice.rest;


import fon.e_dnevnik.classservice.dto.ClassDTO;
import fon.e_dnevnik.classservice.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassController {
    private ClassService classImplementation;

    @Autowired
    public ClassController(ClassService classImplementation) {
        this.classImplementation = classImplementation;
    }

    @GetMapping("{id}")
    public ResponseEntity<ClassDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(classImplementation.findById(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ClassDTO> findByClassTeacherUsername(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(classImplementation.findByClassTeacherUsername(id));
    }
}
