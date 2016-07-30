package com.marcosbarbero.authserver.model.entity;

import com.marcosbarbero.authserver.model.converter.Map2JsonConverter;
import com.marcosbarbero.authserver.model.entity.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Map;

/**
 * @author Marcos Barbero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "oauth_user_details")
public class User {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue
    private Integer id;

    @Column(unique = true, length = 100, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private String authorities;

    @Convert(converter = Map2JsonConverter.class)
    private Map<String, Object> additionalInformation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @PrePersist
    public void encryptPassword() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

}
