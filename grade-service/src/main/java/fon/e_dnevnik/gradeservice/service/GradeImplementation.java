package fon.e_dnevnik.gradeservice.service;

import fon.e_dnevnik.gradeservice.dao.GradeRepository;
import fon.e_dnevnik.gradeservice.dto.GradeDTO;
import fon.e_dnevnik.gradeservice.entity.Grade;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GradeImplementation {

    private GradeRepository gradeRepository;
    private ModelMapper modelMapper;

    public GradeImplementation(GradeRepository gradeRepository, ModelMapper modelMapper) {
        this.gradeRepository = gradeRepository;
        this.modelMapper = modelMapper;
    }

    public GradeDTO save(GradeDTO gradeDTO) throws Exception {
        Grade grade = modelMapper.map(gradeDTO, Grade.class);
        grade.setID(grade.getStudentusername(), grade.getTeacherusername(),0);
        grade.setDate(LocalDate.now());
        Grade savedGrade = gradeRepository.save(grade);
        return modelMapper.map(savedGrade, GradeDTO.class);
    }

    public List<GradeDTO> findByStudentUsername(String username){
        List<Grade> grades = gradeRepository.findByStudentusername(username);
        List<GradeDTO> gradeDTOS = new ArrayList<>();

        for(Grade grade: grades){
            GradeDTO gradeDTO = modelMapper.map(grade, GradeDTO.class);
            gradeDTOS.add(gradeDTO);
        }
        return gradeDTOS;
    }
}
