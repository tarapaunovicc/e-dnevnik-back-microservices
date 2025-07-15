package fon.e_dnevnik.authservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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

    @JoinColumn(name="username1",referencedColumnName = "username")
    @ManyToOne(optional = false)
    @JsonIgnore
    private User userTeacher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getUsername(), teacher.getUsername()) && Objects.equals(getFirstname(), teacher.getFirstname()) && Objects.equals(getLastname(), teacher.getLastname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getFirstname(), getLastname(), getUserTeacher());
    }

}
