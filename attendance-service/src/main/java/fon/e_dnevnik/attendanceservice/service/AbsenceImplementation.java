package fon.e_dnevnik.attendanceservice.service;

import fon.e_dnevnik.attendanceservice.dto.LessonDTO;
import fon.e_dnevnik.attendanceservice.entity.Lesson;
import fon.e_dnevnik.attendanceservice.entity.primarykey.AbsencePK;
import fon.e_dnevnik.attendanceservice.dao.AbsenceRepository;
import fon.e_dnevnik.attendanceservice.dao.LessonRepository;
import fon.e_dnevnik.attendanceservice.dto.AbsenceDTO;
import fon.e_dnevnik.attendanceservice.entity.Absence;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceImplementation  {

    private AbsenceRepository absenceRepository;
    private LessonRepository lessonRepository;

    private ModelMapper modelMapper;
    private final jakarta.persistence.EntityManager em;


    @Autowired
    public AbsenceImplementation(AbsenceRepository absenceRepository, ModelMapper modelMapper, EntityManager em, LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
        this.absenceRepository = absenceRepository;
        this.modelMapper = modelMapper;
        this.em=em;
    }

    public Absence save(AbsenceDTO dto) {
        Absence a = new Absence();
        a.setId(new AbsencePK(dto.getStudentusername(), dto.getLessonid()));
        a.setExcused(dto.isExcused());
        a.setIsfinal(dto.isIsfinal());
        return absenceRepository.saveAndFlush(a);
    }

    @Transactional(readOnly = true)
    public List<AbsenceDTO> findByIdStudentusername(String username){
        List<Absence> absences = absenceRepository.findByIdStudentusername(username);
        System.out.println(absences);
        List<AbsenceDTO> dtoList = new ArrayList<>(absences.size());

        for (Absence a : absences) {
            AbsenceDTO dto = new AbsenceDTO();
            dto.setStudentusername(a.getId().getStudentusername());
            Integer lessonId = a.getId().getLessonid();
            dto.setLessonid(lessonId);
            dto.setExcused(a.isExcused());
            dto.setIsfinal(a.isIsfinal());
            System.out.println(lessonId);
            lessonRepository.findById(lessonId).ifPresent(lesson -> {
                System.out.println(lesson);
                dto.setLesson(modelMapper.map(lesson,LessonDTO.class));
            });

            dtoList.add(dto);
        }
        return dtoList;
    }

    public AbsenceDTO modify(AbsencePK id, boolean excused, boolean isFinal) throws Exception {
        Optional<Absence> optionalAbsence = absenceRepository.findById(id);
        if (optionalAbsence.isPresent()) {
            Absence absence = optionalAbsence.get();
            absence.setExcused(excused);
            absence.setIsfinal(isFinal);
            Absence updatedAbsence = absenceRepository.save(absence);
            return modelMapper.map(updatedAbsence, AbsenceDTO.class);
        } else {
            throw new Exception("Ne postoji odsustvo sa zadatim ID-jem");
        }
    }
}
