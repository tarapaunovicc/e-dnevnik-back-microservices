package fon.e_dnevnik.classservice.service;

import fon.e_dnevnik.classservice.dto.TeacherDTO;
import fon.e_dnevnik.classservice.entity.Class;
import fon.e_dnevnik.classservice.dao.ClassRepository;
import fon.e_dnevnik.classservice.dto.ClassDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final ClassRepository classRepository;

    public ClassService(RestTemplate restTemplate, ModelMapper modelMapper, ClassRepository classRepository) {
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
        this.classRepository = classRepository;
    }

    public ClassDTO findById(Object id) throws Exception {
        Optional<Class> cl=classRepository.findById((int) id);
        ClassDTO classDTO;
        if(cl.isPresent()) {
            classDTO = modelMapper.map(cl.get(), ClassDTO.class);
            return classDTO;
        }
        else{
            throw new Exception("Ne postoji odeljenje");
        }
    }

    public ClassDTO findByClassTeacherUsername(String username)throws Exception {
        Class cl = classRepository.findByClassTeacher(username);
        if (cl != null) {
            return modelMapper.map(cl, ClassDTO.class);
        } else {
            return null;
        }
    }
}
