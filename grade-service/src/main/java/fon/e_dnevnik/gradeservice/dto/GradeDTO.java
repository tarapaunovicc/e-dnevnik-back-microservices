package fon.e_dnevnik.gradeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeDTO {
    private String studentusername;
    private String teacherusername;
    private Integer id;
    private LocalDate date;
    private int grade;
    private String subjectName;
}


