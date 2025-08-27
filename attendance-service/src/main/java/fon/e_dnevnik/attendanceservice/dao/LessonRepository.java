package fon.e_dnevnik.attendanceservice.dao;


import fon.e_dnevnik.attendanceservice.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

}
