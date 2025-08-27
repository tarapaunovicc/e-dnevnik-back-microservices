package fon.e_dnevnik.attendanceservice.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class LessonDTO {

    private Integer lessonid;

    @JsonAlias({"classid","classId"})
    private Integer classid;

    @JsonAlias({"teacherusername","teacherUsername"})
    private String teacherUsername;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonAlias({"classordinalnumber","classOrdinalNumber"})
    private Integer classOrdinalNumber;
    private String curriculum;
}