package fon.e_dnevnik.userservice.service;

import fon.e_dnevnik.userservice.dao.StudentRepository;
import fon.e_dnevnik.userservice.dao.TeacherRepository;
import fon.e_dnevnik.userservice.dto.StudentDTO;
import fon.e_dnevnik.userservice.dto.TeacherDTO;
import fon.e_dnevnik.userservice.dto.UserTokenInfoDTO;
import fon.e_dnevnik.userservice.entity.Student;
import fon.e_dnevnik.userservice.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrentUserService {

    private final RestTemplate restTemplate;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    public CurrentUserService(RestTemplate restTemplate, StudentRepository studentRepository, TeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.restTemplate = restTemplate;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    public Object getCurrentUser(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<UserTokenInfoDTO> response = restTemplate.exchange(
                "http://auth-service/auth/validate",
                HttpMethod.GET,
                request,
                UserTokenInfoDTO.class
                );

        if (response.getStatusCode() == HttpStatus.OK) {
            UserTokenInfoDTO tokenInfo = response.getBody();
            String username = tokenInfo.getUsername();

            if (tokenInfo.getUserType().equals("ROLE_TEACHER")) {
                Teacher teacher = teacherRepository.findTeacherByUserTeacher(username);
                return modelMapper.map(teacher, TeacherDTO.class);
            } else {
                Student student = studentRepository.findStudentByUserStudent(username);
                return modelMapper.map(student, StudentDTO.class);
            }
        }
        return null;
    }
}