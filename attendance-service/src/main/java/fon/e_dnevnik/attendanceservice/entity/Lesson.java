package fon.e_dnevnik.attendanceservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import fon.e_dnevnik.attendanceservice.entity.primarykey.LessonPK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="lesson")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer classid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer lessonid;

    @Column(name="date")
    private LocalDate date;

    @Column(name="classordinalnumber")
    private int classOrdinalNumber;

    @Column(name="curriculum")
    private String curriculum;

    public Lesson(Integer classid, String username, Integer lessonid) {
        this.classid = classid;
        this.username = username;
        this.lessonid = lessonid;
    }
}
