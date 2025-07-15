package fon.e_dnevnik.classservice.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class StudentDTO {
    private String username;
    private String firstname;
    private String lastname;
    private ClassDTO studentClass;
}
