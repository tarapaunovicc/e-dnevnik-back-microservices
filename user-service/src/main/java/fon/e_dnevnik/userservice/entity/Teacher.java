package fon.e_dnevnik.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "teacher")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Teacher implements Serializable {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject", referencedColumnName = "subjectid")
    private Subject subject;


}
