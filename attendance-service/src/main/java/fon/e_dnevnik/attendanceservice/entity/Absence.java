package fon.e_dnevnik.attendanceservice.entity;

import fon.e_dnevnik.attendanceservice.entity.primarykey.AbsencePK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "absence")
public class Absence implements Serializable {

    @EmbeddedId
    private AbsencePK id;

    @Column(name="excused")
    private boolean excused;

    @Column(name="isfinal")
    private boolean isfinal;

    public Absence(AbsencePK id) {
        this.id = id;
    }
    public Absence(String stusername, int id, String tcusername, int lessonid){
        this.id=new AbsencePK(stusername,id,tcusername,lessonid);
    }
}
