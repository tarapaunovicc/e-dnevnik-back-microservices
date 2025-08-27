package fon.e_dnevnik.userservice.rest;

import fon.e_dnevnik.userservice.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CurrentUserService currentUserService;

//    @GetMapping("/me")
//    public ResponseEntity<Object> getCurrentUser(@RequestHeader("Authorization") String token) {
//        Object user = currentUserService.getCurrentUser(token);
//        return ResponseEntity.ok(user);
//    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        Object dto = currentUserService.getCurrentUser(authHeader);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(dto);
}

}
