package fon.e_dnevnik.userservice.dto;

import lombok.Data;

@Data
public class AbsenceDTO {

    private String teacherUsername;
    private int classId;
    private String studentUsername;
    private Integer lessonId;

    private boolean excused;
    private boolean isFinal;
    private int lesson;
}
