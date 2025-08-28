package fon.e_dnevnik.userservice.dto;

import lombok.Data;


@Data
public class TeacherDTO {

    private String username;
    private String firstname;
    private String lastname;
    private SubjectDTO subject;

}
