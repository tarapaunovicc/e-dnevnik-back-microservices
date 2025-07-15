package fon.e_dnevnik.classservice.service;

import fon.e_dnevnik.classservice.dao.TeachersClassesRepository;
import fon.e_dnevnik.classservice.dto.ClassDTO;
import fon.e_dnevnik.classservice.dto.TeacherDTO;
import fon.e_dnevnik.classservice.dto.TeachersClassesDTO;
import fon.e_dnevnik.classservice.entity.TeachersClasses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeachersClassesService{
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final TeachersClassesRepository teachersClassesRepository;
    @Autowired
    public TeachersClassesService(RestTemplate restTemplate, ModelMapper modelMapper, TeachersClassesRepository teachersClassesRepository) {
        this.modelMapper = modelMapper;
        this.teachersClassesRepository = teachersClassesRepository;
        this.restTemplate=restTemplate;
    }
    public List<TeachersClassesDTO> findByTeacherUsername(String username) {
        List<TeachersClasses> teachersClasses = teachersClassesRepository.findByIdTeacherusername(username);
        List<TeachersClassesDTO> teachersClassesDTOS = new ArrayList<>();
        for(TeachersClasses g: teachersClasses){
            TeachersClassesDTO teachersClassesDTO = modelMapper.map(g, TeachersClassesDTO.class);
           // teachersClassesDTO.setCl(modelMapper.map(g.getCl(), ClassDTO.class));
           // teachersClassesDTO.setTeacher(modelMapper.map(g.getTeacher(), TeacherDTO.class));
            teachersClassesDTOS.add(teachersClassesDTO);
        }
        return teachersClassesDTOS;
    }
}
