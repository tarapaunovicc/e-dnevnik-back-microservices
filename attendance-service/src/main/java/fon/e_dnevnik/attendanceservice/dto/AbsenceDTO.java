package fon.e_dnevnik.attendanceservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fon.e_dnevnik.attendanceservice.entity.primarykey.AbsencePK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonAlias;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceDTO {

    private String studentusername;
    @JsonAlias({"lesson_id","lessonId"})
    private Integer lessonid;
    private boolean excused;
    private boolean isfinal;
    private LessonDTO lesson;
    private String subjectname;

    @JsonIgnore
    public AbsencePK getId() {
        return new AbsencePK(studentusername, lessonid);
    }
}
