package fon.e_dnevnik.userservice.dao;


import fon.e_dnevnik.userservice.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    @Query("select t from Teacher t left join fetch t.subject where t.username = :username")
    Optional<Teacher> findByUsernameWithSubject(@Param("username") String username);
}
