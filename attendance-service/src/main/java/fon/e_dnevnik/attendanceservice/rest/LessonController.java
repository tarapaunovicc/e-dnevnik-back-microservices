package fon.e_dnevnik.attendanceservice.rest;

import fon.e_dnevnik.attendanceservice.dto.LessonDTO;
import fon.e_dnevnik.attendanceservice.service.LessonImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private LessonImplementation lessonImplementation;

    @Autowired
    public LessonController(LessonImplementation lessonImplementation) {
        this.lessonImplementation = lessonImplementation;
    }

    @PostMapping("/new")
    public ResponseEntity<LessonDTO> save( @RequestBody LessonDTO lessonDTO) throws Exception {
        return new ResponseEntity<>(lessonImplementation.save(lessonDTO), HttpStatus.CREATED);
    }
}
