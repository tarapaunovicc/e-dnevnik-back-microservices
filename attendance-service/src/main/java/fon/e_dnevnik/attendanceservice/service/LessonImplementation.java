package fon.e_dnevnik.attendanceservice.service;

import fon.e_dnevnik.attendanceservice.dao.AbsenceRepository;
import fon.e_dnevnik.attendanceservice.dao.LessonRepository;
import fon.e_dnevnik.attendanceservice.dto.AbsenceDTO;
import fon.e_dnevnik.attendanceservice.dto.help.NewLessonWithAbsencesResponse;
import fon.e_dnevnik.attendanceservice.entity.Absence;
import fon.e_dnevnik.attendanceservice.entity.primarykey.AbsencePK;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.LinkedHashSet;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.attendanceservice.entity.Lesson;
import fon.e_dnevnik.attendanceservice.dto.LessonDTO;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonImplementation{

    private final LessonRepository lessonRepository;
    private final AbsenceRepository absenceRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public LessonDTO save(LessonDTO dto) {
        if (dto.getClassid() == null) throw new IllegalArgumentException("classId je obavezan");
        if (dto.getTeacherUsername() == null || dto.getTeacherUsername().isBlank())
            throw new IllegalArgumentException("teacherUsername je obavezan");
        if (dto.getDate() == null) throw new IllegalArgumentException("date je obavezan (format yyyy-MM-dd)");

        Lesson e = new Lesson();
        e.setClassid(dto.getClassid());
        e.setTeacherUsername(dto.getTeacherUsername());
        e.setDate(dto.getDate());
        e.setClassOrdinalNumber(dto.getClassOrdinalNumber());
        e.setCurriculum(dto.getCurriculum());

        e = lessonRepository.save(e);

        LessonDTO out = new LessonDTO();
        out.setLessonid(e.getLessonid());
        out.setClassid(e.getClassid());
        out.setTeacherUsername(e.getTeacherUsername());
        out.setDate(e.getDate());
        out.setClassOrdinalNumber(e.getClassOrdinalNumber());
        out.setCurriculum(e.getCurriculum());
        return out;
    }
    @Transactional
    public NewLessonWithAbsencesResponse createLessonAndAbsences(LessonDTO lessonDTO, List<String> studentUsernames) {

        Lesson lesson = new Lesson();
        lesson.setClassid(lessonDTO.getClassid());
        lesson.setTeacherUsername(lessonDTO.getTeacherUsername());
        lesson.setDate(lessonDTO.getDate());
        lesson.setClassOrdinalNumber(lessonDTO.getClassOrdinalNumber());
        lesson.setCurriculum(lessonDTO.getCurriculum());

        Lesson savedLesson = lessonRepository.save(lesson);
        LessonDTO savedLessonDTO = modelMapper.map(savedLesson, LessonDTO.class);

        Set<String> uniqueStudents = (studentUsernames == null)
                ? Set.of()
                : studentUsernames.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        List<Absence> toSave = new ArrayList<>(uniqueStudents.size());
        for (String su : uniqueStudents) {
            Absence a = new Absence();
            a.setId(new AbsencePK(su, savedLesson.getLessonid()));
            a.setExcused(false);
            a.setIsfinal(false);
            toSave.add(a);
        }

        List<Absence> savedAbsences = toSave.isEmpty() ? List.of() : absenceRepository.saveAll(toSave);

        List<AbsenceDTO> absenceDTOs = new ArrayList<>(savedAbsences.size());
        for (Absence a : savedAbsences) {
            AbsenceDTO dto = new AbsenceDTO();
            dto.setStudentusername(a.getId().getStudentusername());
            dto.setLessonid(a.getId().getLessonid());
            dto.setExcused(a.isExcused());
            dto.setIsfinal(a.isIsfinal());
            dto.setLesson(savedLessonDTO);
            absenceDTOs.add(dto);
        }

        NewLessonWithAbsencesResponse out = new NewLessonWithAbsencesResponse();
        out.setLesson(savedLessonDTO);
        out.setAbsences(absenceDTOs);
        return out;
    }

}
