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

}
