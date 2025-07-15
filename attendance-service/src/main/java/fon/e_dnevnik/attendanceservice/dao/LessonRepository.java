package fon.e_dnevnik.attendanceservice.dao;


import fon.e_dnevnik.attendanceservice.entity.Lesson;
import fon.e_dnevnik.attendanceservice.entity.primarykey.LessonPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, LessonPK> {

}
