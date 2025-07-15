package fon.e_dnevnik.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Collection;

@Data
public class TeacherDTO {

    private String username;
    private String firstname;
    private String lastname;
    private SubjectDTO subject;

    private Collection<TeachersClassesDTO> classes; //OVDE TI JE GRESKA U MONOLITU
}
