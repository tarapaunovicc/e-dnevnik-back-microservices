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
//    List<TeachersClasses> findByIdTeacherusername(String username);
    @Query("select tc from TeachersClasses tc where tc.id.teacherusername = :username")
    List<TeachersClasses> findAllByTeacherUsername(@Param("username") String username);

    @Query(
            value = "SELECT tc.classid, tc.teacherusername " +
                    "FROM teachersclasses tc " +
                    "WHERE tc.teacherusername = :username",
            nativeQuery = true
    )
    List<TeachersClasses> findNativeByTeacher(@Param("username") String username);

    @Query("""
           select tc
           from TeachersClasses tc
           join fetch tc.cl c
           where tc.id.teacherusername = :username
           """)
    List<TeachersClasses> findAllByTeacherUsernameFetchClass(@Param("username") String username);
}
