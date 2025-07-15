package fon.e_dnevnik.authservice.dao;

import fon.e_dnevnik.authservice.entity.Student;
import fon.e_dnevnik.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Student findStudentByUserStudent(User user);
    Student findStudentByUserStudentUsername(String username);
}
