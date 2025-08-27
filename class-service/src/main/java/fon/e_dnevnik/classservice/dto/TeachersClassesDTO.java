package fon.e_dnevnik.classservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fon.e_dnevnik.classservice.entity.primarykey.TeachersClassesPK;
import jakarta.persistence.Basic;
import lombok.Data;

@Data
public class TeachersClassesDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int classid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherusername;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClassDTO cl;
}
