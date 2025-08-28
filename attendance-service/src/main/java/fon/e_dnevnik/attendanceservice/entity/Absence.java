package fon.e_dnevnik.attendanceservice.entity;

import fon.e_dnevnik.attendanceservice.entity.primarykey.AbsencePK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(
        name = "absence",
        indexes = {
                @Index(name = "idx_absence_lesson", columnList = "lesson_id"),
                @Index(name = "idx_absence_student", columnList = "studentusername")
        }
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Absence implements Serializable {

    @EmbeddedId
    private AbsencePK id;

    @Column(name = "excused", nullable = false)
    private boolean excused;

    @Column(name = "isfinal", nullable = false)
    private boolean isfinal;


}