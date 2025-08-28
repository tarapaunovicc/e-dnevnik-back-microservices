package fon.e_dnevnik.classservice.dao;

import fon.e_dnevnik.classservice.entity.TeachersClasses;
import fon.e_dnevnik.classservice.entity.primarykey.TeachersClassesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachersClassesRepository extends JpaRepository<TeachersClasses, TeachersClassesPK> {
    @Query("""
           select tc
           from TeachersClasses tc
           join fetch tc.cl c
           where tc.id.teacherusername = :username
           """)
    List<TeachersClasses> findAllByTeacherUsernameFetchClass(@Param("username") String username);
}
