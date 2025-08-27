package fon.e_dnevnik.attendanceservice.entity.primarykey;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor
public class AbsencePK implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Basic(optional = false)
    @Column(name = "studentusername", length = 255, nullable = false)
    private String studentusername;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Basic(optional = false)
    @Column(name = "lesson_id", nullable = false)
    private Integer lessonid;
}
