package fon.e_dnevnik.attendanceservice.dto.help;

import fon.e_dnevnik.attendanceservice.dto.AbsenceDTO;
import fon.e_dnevnik.attendanceservice.dto.LessonDTO;
import lombok.Data;
import java.util.List;

@Data
public class NewLessonWithAbsencesResponse {
    private LessonDTO lesson;
    private List<AbsenceDTO> absences;
}