package fon.e_dnevnik.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectDTO {

    private Integer subjectid;
    private String name;
}
