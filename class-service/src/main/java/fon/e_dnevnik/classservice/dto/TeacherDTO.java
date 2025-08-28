package fon.e_dnevnik.classservice.dto;

import lombok.Data;

@Data
public class TeacherDTO {
    private String username;
    private String firstname;
    private String lastname;
    private SubjectDTO subject;
}
