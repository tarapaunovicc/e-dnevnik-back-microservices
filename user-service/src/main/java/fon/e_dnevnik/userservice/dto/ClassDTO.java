package fon.e_dnevnik.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClassDTO {
    private int classid;
    private int grade;
    private int number;
}
