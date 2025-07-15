package fon.e_dnevnik.attendanceservice.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class LessonDTO {

    private Integer classid;

    private String username;

    private Integer lessonid;

    private LocalDate date;

    private int classOrdinalNumber;

    private String curriculum;

    private TeacherDTO teacher;

    private ClassDTO cl;
}
