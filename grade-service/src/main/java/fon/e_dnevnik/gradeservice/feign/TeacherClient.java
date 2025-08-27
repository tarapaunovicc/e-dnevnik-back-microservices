package fon.e_dnevnik.gradeservice.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(name = "user-service", path = "/teachers")
public interface TeacherClient {

    @GetMapping("/{username}/subject")
    SubjectDTO getSubject(@PathVariable("username") String username);

    @lombok.Data
    class SubjectDTO {
        private Integer subjectid;
        private String name;
    }
}