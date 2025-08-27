package fon.e_dnevnik.gradeservice.dto.help;

import lombok.Data;

@Data
public class GradeFilterRequest {
    private String studentusername;
    private String teacherusername;
}