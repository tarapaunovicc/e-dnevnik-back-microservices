package fon.e_dnevnik.userservice.service;

import fon.e_dnevnik.userservice.dao.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.userservice.dto.StudentDTO;
import fon.e_dnevnik.userservice.entity.Student;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public StudentDTO findById(Object id) throws Exception {
        Optional<Student> student = studentRepository.findById((String) id);
        if (student.isPresent()) {
            return modelMapper.map(student.get(), StudentDTO.class);
        } else {
            throw new Exception("Student not found");
        }
    }
    public List<StudentDTO> findByStudentClassClassId(int classId) {
        List<Student> students = studentRepository.findByStudentClass(classId);
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }
}
