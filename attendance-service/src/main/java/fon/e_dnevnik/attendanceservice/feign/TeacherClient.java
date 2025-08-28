package fon.e_dnevnik.attendanceservice.feign;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(name = "user-service", path = "/teachers")
public interface TeacherClient {
    @GetMapping("/{username}/subject")
    SubjectDTO getSubject(@PathVariable("username") String username);

    @Data
    class SubjectDTO {
        private String name;
    }
}