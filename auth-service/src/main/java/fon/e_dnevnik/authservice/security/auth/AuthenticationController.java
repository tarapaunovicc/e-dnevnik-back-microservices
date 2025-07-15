package fon.e_dnevnik.authservice.security.auth;

import fon.e_dnevnik.authservice.dto.UserTokenInfoDTO;
import fon.e_dnevnik.authservice.security.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@Valid @RequestBody AuthenticationRequest request){
        System.out.println("Prvo");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<UserTokenInfoDTO> validateToken(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        if (jwtService.isTokenExpired(jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = jwtService.extractUsername(jwt);
        String userType = jwtService.extractUserType(jwt);

        UserTokenInfoDTO dto = new UserTokenInfoDTO(username, userType);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
}
}