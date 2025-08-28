package fon.e_dnevnik.gradeservice.service;

import fon.e_dnevnik.gradeservice.dao.GradeRepository;
import fon.e_dnevnik.gradeservice.dto.GradeDTO;
import fon.e_dnevnik.gradeservice.entity.Grade;
import fon.e_dnevnik.gradeservice.feign.TeacherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GradeImplementation {

    private final GradeRepository gradeRepository;
    private final org.modelmapper.ModelMapper modelMapper;
    private final TeacherClient teacherClient;
    public GradeDTO save(GradeDTO dto) {
        if (dto.getStudentusername() == null || dto.getTeacherusername() == null) {
            throw new IllegalArgumentException("studentusername i teacherusername su obavezni.");
        }
        if (dto.getGrade() < 1 || dto.getGrade() > 5) {
            throw new IllegalArgumentException("Ocena mora biti 1-5.");
        }

        Grade entity = new Grade();
        entity.setStudentusername(dto.getStudentusername());
        entity.setTeacherusername(dto.getTeacherusername());
        entity.setGrade(dto.getGrade());
        entity.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());

        Grade saved = gradeRepository.save(entity);
        return toDto(saved);
    }
    public List<GradeDTO> findByStudentUsername(String username) {
        return gradeRepository.findByStudentusername(username)
                .stream()
                .map(this::toDto)
                .toList();
    }
    public List<GradeDTO> findByStudentUsernameWithSubject(String username) {
        var grades = gradeRepository.findByStudentusername(username);

        var uniqueTeachers = grades.stream()
                .map(Grade::getTeacherusername)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<String, String> subjectByTeacher = new HashMap<>();
        for (String t : uniqueTeachers) {
            try {
                TeacherClient.SubjectDTO subject = teacherClient.getSubject(t);
                subjectByTeacher.put(t, subject != null ? subject.getName() : null);
            } catch (Exception ex) {
                System.err.println("[Feign] getSubject(" + t + ") failed: " + ex);
                subjectByTeacher.put(t, null);
            }
        }

        return grades.stream()
                .map(g -> {
                    GradeDTO dto = toDto(g);
                    dto.setSubjectName(subjectByTeacher.get(g.getTeacherusername()));
                    return dto;
                })
                .toList();
    }
    public List<GradeDTO> findByStudentAndTeacher(String studentUsername, String teacherUsername) {
        List<Grade> grades = gradeRepository.findByStudentusernameAndTeacherusername(studentUsername, teacherUsername);
        return grades.stream()
                .map(grade -> modelMapper.map(grade, GradeDTO.class))
                .collect(Collectors.toList());
    }
    private GradeDTO toDto(Grade g) {
        GradeDTO dto = new GradeDTO();
        dto.setId(g.getId());
        dto.setStudentusername(g.getStudentusername());
        dto.setTeacherusername(g.getTeacherusername());
        dto.setDate(g.getDate());
        dto.setGrade(g.getGrade());
        return dto;
    }
}