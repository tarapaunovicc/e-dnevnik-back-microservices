package fon.e_dnevnik.userservice.dto;

import lombok.Data;


@Data
public class StudentDTO {

    private String username;
    private String firstname;
    private String lastname;
    private String UMCN;
    private ClassDTO studentClass;
}
