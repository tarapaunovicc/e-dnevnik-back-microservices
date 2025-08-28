package fon.e_dnevnik.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;


@Entity
@Table(name = "student")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Student implements Serializable {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "umcn")
    private String umcn;

    @Column(name = "studentclass", nullable = false)
    private Integer studentClass;
}
