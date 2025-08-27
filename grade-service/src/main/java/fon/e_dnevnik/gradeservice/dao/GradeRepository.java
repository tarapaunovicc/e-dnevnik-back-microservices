package fon.e_dnevnik.gradeservice.dao;

import fon.e_dnevnik.gradeservice.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findByStudentusername(String studentusername);
    List<Grade> findByStudentusernameAndTeacherusername(String studentusername, String teacherusername);
}