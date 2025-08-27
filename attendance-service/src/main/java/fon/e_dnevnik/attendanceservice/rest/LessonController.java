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

    @PostMapping(
            value = "/new",
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LessonDTO> save(@RequestBody LessonDTO lessonDTO) {
        System.out.println(lessonDTO);
        LessonDTO created = lessonImplementation.save(lessonDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
