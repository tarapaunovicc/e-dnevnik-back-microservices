package fon.e_dnevnik.attendanceservice.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private String username;
    private String firstname;
    private String lastname;
    private ClassDTO studentClass;
}
