package fon.e_dnevnik.attendanceservice.rest;

import fon.e_dnevnik.attendanceservice.dto.AbsenceDTO;
import fon.e_dnevnik.attendanceservice.service.AbsenceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {
    private AbsenceImplementation absenceImplementation;


    @Autowired
    public AbsenceController(AbsenceImplementation absenceImplementation) {
        this.absenceImplementation = absenceImplementation;
    }

    @PostMapping("/new")
    public ResponseEntity<AbsenceDTO> save(@RequestBody AbsenceDTO absenceDTO) throws Exception {
        return new ResponseEntity<>(absenceImplementation.save(absenceDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all/{username}")
    public List<AbsenceDTO> findByStudentUsername(@PathVariable String username) {
        return absenceImplementation.findByIdStudentusername(username);
    }

    @PutMapping("/modify")
    public ResponseEntity<AbsenceDTO> modifyAbsence(@RequestBody AbsenceDTO absenceDTO) throws Exception {
        AbsenceDTO updatedAbsence = absenceImplementation.modify(absenceDTO.getId(), absenceDTO.isExcused(), absenceDTO.isIsfinal());
        return ResponseEntity.ok(updatedAbsence);
    }

}
