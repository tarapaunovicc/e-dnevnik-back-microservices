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
@Table(name="student")
public class Student implements Serializable {

    @Id
    @Column(name="username")
    private String username;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="umcn")
    private String UMCN;

    @Column(name="studentclass")
    private int studentClass;

    @Column(name="username1")
    private String userStudent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getUsername(), student.getUsername()) && Objects.equals(getFirstname(), student.getFirstname()) && Objects.equals(getLastname(), student.getLastname()) && Objects.equals(getUMCN(), student.getUMCN()) && Objects.equals(getStudentClass(), student.getStudentClass())
                && Objects.equals(getUserStudent(), student.getUserStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getFirstname(), getLastname(), getUMCN(), getStudentClass(), getUserStudent());
    }
}
