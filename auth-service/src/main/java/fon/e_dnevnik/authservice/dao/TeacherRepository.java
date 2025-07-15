package fon.e_dnevnik.authservice.dao;


import fon.e_dnevnik.authservice.entity.Teacher;
import fon.e_dnevnik.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    Teacher findTeacherByUserTeacher(User user);
    Teacher findTeacherByUserTeacherUsername(String username);
}
