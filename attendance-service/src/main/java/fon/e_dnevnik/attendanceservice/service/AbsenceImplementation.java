package fon.e_dnevnik.attendanceservice.service;

import fon.e_dnevnik.attendanceservice.dto.LessonDTO;
import fon.e_dnevnik.attendanceservice.entity.Lesson;
import fon.e_dnevnik.attendanceservice.entity.primarykey.AbsencePK;
import fon.e_dnevnik.attendanceservice.dao.AbsenceRepository;
import fon.e_dnevnik.attendanceservice.dao.LessonRepository;
import fon.e_dnevnik.attendanceservice.dto.AbsenceDTO;
import fon.e_dnevnik.attendanceservice.entity.Absence;
import fon.e_dnevnik.attendanceservice.feign.TeacherClient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AbsenceImplementation  {

    private final AbsenceRepository absenceRepository;
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;
    private final TeacherClient teacherClient;

    public Absence save(AbsenceDTO dto) {
        Absence a = new Absence();
        a.setId(new AbsencePK(dto.getStudentusername(), dto.getLessonid()));
        a.setExcused(dto.isExcused());
        a.setIsfinal(dto.isIsfinal());
        return absenceRepository.saveAndFlush(a);
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
    @Transactional(readOnly = true)
    public List<AbsenceDTO> findByIdStudentusername(String username) {
        List<Absence> absences = absenceRepository.findByIdStudentusername(username);
        if (absences.isEmpty()) return List.of();

        Set<Integer> lessonIds = absences.stream()
                .map(a -> a.getId().getLessonid())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<Lesson> lessons = lessonRepository.findAllById(lessonIds);
        Map<Integer, Lesson> lessonById = lessons.stream()
                .collect(Collectors.toMap(Lesson::getLessonid, l -> l));

        Set<String> teacherUsernames = lessons.stream()
                .map(Lesson::getTeacherUsername)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<String, String> subjectByTeacher = new HashMap<>(teacherUsernames.size());
        for (String t : teacherUsernames) {
            try {
                TeacherClient.SubjectDTO s = teacherClient.getSubject(t);
                subjectByTeacher.put(t, s != null ? s.getName() : null);
            } catch (Exception e) {
                subjectByTeacher.put(t, null);
            }
        }

        List<AbsenceDTO> dtoList = new ArrayList<>(absences.size());
        for (Absence a : absences) {
            AbsenceDTO dto = new AbsenceDTO();
            dto.setStudentusername(a.getId().getStudentusername());
            Integer lessonId = a.getId().getLessonid();
            dto.setLessonid(lessonId);
            dto.setExcused(a.isExcused());
            dto.setIsfinal(a.isIsfinal());

            Lesson l = lessonById.get(lessonId);
            if (l != null) {
                dto.setLesson(modelMapper.map(l, LessonDTO.class));
                String subjName = subjectByTeacher.get(l.getTeacherUsername());
                dto.setSubjectname(subjName);
            } else {
                dto.setLesson(null);
                dto.setSubjectname(null);
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}
