package fon.e_dnevnik.classservice.entity.primarykey;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachersClassesPK implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Basic(optional = false)
    @Column(name = "classid", nullable = false)
    private int classid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Basic(optional = false)
    @Column(name = "teacherusername", nullable = false)
    private String teacherusername;
}
