package fon.e_dnevnik.attendanceservice.service;

import fon.e_dnevnik.attendanceservice.dao.LessonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.attendanceservice.entity.Lesson;
import fon.e_dnevnik.attendanceservice.dto.LessonDTO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LessonImplementation{

    private LessonRepository lessonRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LessonImplementation(LessonRepository lessonRepository, ModelMapper modelMapper) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
    }

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
}
