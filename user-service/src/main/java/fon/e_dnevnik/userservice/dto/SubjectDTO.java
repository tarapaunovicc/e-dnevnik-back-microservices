package fon.e_dnevnik.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectDTO {

    private int subjectId;
    private String name;
}
