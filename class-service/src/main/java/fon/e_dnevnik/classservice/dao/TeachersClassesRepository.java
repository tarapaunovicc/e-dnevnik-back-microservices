package fon.e_dnevnik.classservice.dao;

import fon.e_dnevnik.classservice.entity.TeachersClasses;
import fon.e_dnevnik.classservice.entity.primarykey.TeachersClassesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachersClassesRepository extends JpaRepository<TeachersClasses, TeachersClassesPK> {
    List<TeachersClasses> findByIdTeacherusername(String username);
}
