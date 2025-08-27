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
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

//
@GetMapping("/validate")
public ResponseEntity<UserTokenInfoDTO> validateToken(@RequestHeader("Authorization") String authHeader) {
    try {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String jwt = authHeader.substring(7);

        if (jwtService.isTokenExpired(jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = jwtService.extractUsername(jwt);
        String userType = jwtService.extractUserType(jwt);

        return ResponseEntity.ok(new UserTokenInfoDTO(username, userType));
    } catch (io.jsonwebtoken.ExpiredJwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}



    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
}
}