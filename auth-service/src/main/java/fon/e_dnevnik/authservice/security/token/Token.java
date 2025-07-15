package fon.e_dnevnik.authservice.security.token;

import fon.e_dnevnik.authservice.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name="token")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name="token",unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user",referencedColumnName = "username")
    public User user;
}