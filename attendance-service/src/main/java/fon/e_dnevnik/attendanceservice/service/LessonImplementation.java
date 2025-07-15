package fon.e_dnevnik.attendanceservice.service;

import fon.e_dnevnik.attendanceservice.dao.LessonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.attendanceservice.entity.Lesson;
import fon.e_dnevnik.attendanceservice.dto.LessonDTO;

@Service
public class LessonImplementation{

    private LessonRepository lessonRepository;
    private ModelMapper modelMapper;

    @Autowired
    public LessonImplementation(LessonRepository lessonRepository, ModelMapper modelMapper) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
    }

    public LessonDTO save(LessonDTO lessonDTO) throws Exception {
        Lesson lesson = modelMapper.map(lessonDTO, Lesson.class);
        Lesson savedLesson = lessonRepository.save(lesson);
        return modelMapper.map(savedLesson, LessonDTO.class);    }
}
