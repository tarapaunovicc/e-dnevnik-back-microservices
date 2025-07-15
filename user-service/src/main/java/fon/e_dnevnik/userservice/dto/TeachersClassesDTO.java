package fon.e_dnevnik.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class TeachersClassesDTO {
    private int classid;
    private String teacherusername;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClassDTO cl;
}
