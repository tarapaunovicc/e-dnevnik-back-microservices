package fon.e_dnevnik.classservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import fon.e_dnevnik.classservice.entity.primarykey.TeachersClassesPK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="teachersclasses")
public class TeachersClasses implements Serializable {

    @EmbeddedId
    private TeachersClassesPK id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "classid", referencedColumnName = "classid", insertable = false, updatable = false)
    private Class cl;
}
