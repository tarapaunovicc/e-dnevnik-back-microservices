package fon.e_dnevnik.gradeservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "grade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String studentusername;

    @Column(nullable = false)
    private String teacherusername;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int grade;
}