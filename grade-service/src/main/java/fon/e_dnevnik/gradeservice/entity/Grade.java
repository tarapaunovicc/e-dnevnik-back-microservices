package fon.e_dnevnik.gradeservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "grade")
public class Grade implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String studentusername;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teacherusername;

    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gradeid;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "grade")
    private int grade;

    public Grade(String studentusername, String teacherusername, Integer gradeid) {
        this.studentusername = studentusername;
        this.teacherusername = teacherusername;
        this.gradeid = gradeid;
    }

    public void setID(String studentusername, String teacherusername, Integer gradeid) {
        this.studentusername = studentusername;
        this.teacherusername = teacherusername;
        this.gradeid = gradeid;
    }
}
