package fon.e_dnevnik.userservice.service;

import fon.e_dnevnik.userservice.dao.StudentRepository;
import fon.e_dnevnik.userservice.dao.TeacherRepository;
import fon.e_dnevnik.userservice.dto.StudentDTO;
import fon.e_dnevnik.userservice.dto.TeacherDTO;
import fon.e_dnevnik.userservice.dto.UserTokenInfoDTO;
import fon.e_dnevnik.userservice.entity.Student;
import fon.e_dnevnik.userservice.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Service
public class CurrentUserService {

    private final RestTemplate restTemplate;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

    public CurrentUserService(RestTemplate restTemplate, StudentRepository studentRepository,
                              TeacherRepository teacherRepository, ModelMapper modelMapper) {
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

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            var tokenInfo = response.getBody();
            var username = tokenInfo.getUsername();

            if ("ROLE_TEACHER".equals(tokenInfo.getUserType())) {
                var teacher = teacherRepository.findByUsernameWithSubject(username)
                        .orElseThrow(() -> new NoSuchElementException("Teacher not found: " + username));

                return modelMapper.map(teacher, TeacherDTO.class);
            } else {
                var student = studentRepository.findByUsername(username)
                        .orElseThrow(() -> new NoSuchElementException("Student not found: " + username));
                return modelMapper.map(student, StudentDTO.class);
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized");
    }
}
