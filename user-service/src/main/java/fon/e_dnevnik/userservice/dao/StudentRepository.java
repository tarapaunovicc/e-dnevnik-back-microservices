package fon.e_dnevnik.userservice.dao;

;
import fon.e_dnevnik.userservice.entity.Student;
import fon.e_dnevnik.userservice.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    //List<Student> findByStudentClassClassId(int classId);
    Student findStudentByUserStudent(String userStudent);

    //Student findStudentByUserStudentUsername(String username);
}
