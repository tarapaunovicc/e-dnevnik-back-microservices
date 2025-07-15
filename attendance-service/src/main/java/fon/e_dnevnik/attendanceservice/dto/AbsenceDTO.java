package fon.e_dnevnik.attendanceservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fon.e_dnevnik.attendanceservice.entity.primarykey.AbsencePK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceDTO {

    private String teacherusername;

    private int classid;

    private String studentusername;

    private Integer lessonid;

    private boolean excused;

    private boolean isfinal;

    private StudentDTO student;

    private LessonDTO lesson;

    @JsonIgnore
    public AbsencePK getId() {
        return new AbsencePK(teacherusername, classid, studentusername, lessonid);
    }

}
