package fon.e_dnevnik.classservice.service;

import fon.e_dnevnik.classservice.dao.TeachersClassesRepository;
import fon.e_dnevnik.classservice.dto.ClassDTO;
import fon.e_dnevnik.classservice.dto.TeacherDTO;
import fon.e_dnevnik.classservice.dto.TeachersClassesDTO;
import fon.e_dnevnik.classservice.entity.TeachersClasses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
//    public List<TeachersClassesDTO> findByTeacherUsername(String username) {
//        List<TeachersClasses> teachersClasses = teachersClassesRepository.findNativeByTeacher(username);
//        List<TeachersClassesDTO> teachersClassesDTOS = new ArrayList<>();
//        for(TeachersClasses g: teachersClasses){
//            TeachersClassesDTO teachersClassesDTO = modelMapper.map(g, TeachersClassesDTO.class);
//            teachersClassesDTO.setClassid(g.getId().getClassid());
//            teachersClassesDTO.setTeacherusername(g.getId().getTeacherusername());
//            teachersClassesDTOS.add(teachersClassesDTO);
//        }
//        return teachersClassesDTOS;
//    }
@Transactional(readOnly = true)
public List<TeachersClassesDTO> findByTeacherUsername(String username) {
    List<TeachersClasses> rows = teachersClassesRepository.findAllByTeacherUsernameFetchClass(username);
    List<TeachersClassesDTO> out = new ArrayList<>(rows.size());
    for (TeachersClasses tc : rows) {
        TeachersClassesDTO dto = new TeachersClassesDTO();
        dto.setClassid(tc.getId().getClassid());
        dto.setTeacherusername(tc.getId().getTeacherusername());
        dto.setCl(modelMapper.map(tc.getCl(), ClassDTO.class));
        out.add(dto);
    }
    return out;
}
}
