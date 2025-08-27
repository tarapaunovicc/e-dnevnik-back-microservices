package fon.e_dnevnik.attendanceservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(
        name = "lesson",
        indexes = {
                @Index(name = "idx_lesson_class_teacher_date", columnList = "classid, teacherusername, date"),
                @Index(name = "idx_lesson_class_ord", columnList = "classid, date, classordinalnumber")
        }
)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Lesson implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Integer lessonid;

    @Column(name = "classid", nullable = false)
    private Integer classid;

    @Column(name = "teacherusername", nullable = false, length = 255)
    private String teacherUsername;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "classordinalnumber", nullable = false)
    private int classOrdinalNumber;

    @Column(name = "curriculum")
    private String curriculum;
}