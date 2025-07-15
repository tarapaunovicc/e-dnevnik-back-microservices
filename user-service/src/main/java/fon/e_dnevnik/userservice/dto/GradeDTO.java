package fon.e_dnevnik.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GradeDTO {

    private String studentUsername;
    private String teacherUsername;
    private Integer gradeId;
    private LocalDate date;
    private int grade;
}

