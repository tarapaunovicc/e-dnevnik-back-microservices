package fon.e_dnevnik.attendanceservice.dao;

import fon.e_dnevnik.attendanceservice.entity.Absence;
import fon.e_dnevnik.attendanceservice.entity.primarykey.AbsencePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, AbsencePK> {
    @Transactional(readOnly = true)
    List<Absence> findByIdStudentusername(String username);
}
