package fon.e_dnevnik.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTokenInfoDTO {
    private String username;
    private String userType;
}