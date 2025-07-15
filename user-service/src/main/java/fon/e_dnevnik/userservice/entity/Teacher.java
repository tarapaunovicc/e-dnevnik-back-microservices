package fon.e_dnevnik.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="teacher")
public class Teacher implements Serializable {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="subject") //ili je subjectid
    private int subject;

    @Column(name="username1")
    private String userTeacher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getUsername(), teacher.getUsername()) && Objects.equals(getFirstname(), teacher.getFirstname()) && Objects.equals(getLastname(), teacher.getLastname())
                && Objects.equals(getSubject(), teacher.getSubject()) && Objects.equals(getUserTeacher(), teacher.getUserTeacher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getFirstname(), getLastname(), getSubject(), getUserTeacher());
    }
}
