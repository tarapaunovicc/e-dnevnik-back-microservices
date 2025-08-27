package fon.e_dnevnik.userservice.service;

import fon.e_dnevnik.userservice.dao.TeacherRepository;
import fon.e_dnevnik.userservice.dto.SubjectDTO;
import fon.e_dnevnik.userservice.dto.TeacherDTO;
import fon.e_dnevnik.userservice.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    public TeacherDTO findById(Object id) throws Exception {
        Optional<Teacher> teacherOpt = teacherRepository.findById((String) id);
        if (teacherOpt.isEmpty()) {
            throw new Exception("Ne postoji profesor");
        }
        Teacher teacher = teacherOpt.get();
        TeacherDTO teacherDTO = modelMapper.map(teacher, TeacherDTO.class);
        if (teacher.getSubject() != null) {
            SubjectDTO subjectDTO = modelMapper.map(teacher.getSubject(), SubjectDTO.class);
            teacherDTO.setSubject(subjectDTO);
        } else {
            teacherDTO.setSubject(null);
        }

        return teacherDTO;
    }

    public SubjectDTO getSubject(@PathVariable String username) {
        var teacher = teacherRepository.findByUsernameWithSubject(username)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Teacher not found"));
        SubjectDTO subjectdto = new SubjectDTO();
        subjectdto.setName(teacher.getSubject().getName());
        subjectdto.setSubjectid(teacher.getSubject().getSubjectid());
        return subjectdto;
    }
}
