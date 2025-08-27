package fon.e_dnevnik.userservice.dao;

;
import fon.e_dnevnik.userservice.entity.Student;
import fon.e_dnevnik.userservice.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByUsername(String userStudent);
    List<Student> findByStudentClass(Integer classId);
}
