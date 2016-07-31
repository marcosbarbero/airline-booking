package com.marcosbarbero.authserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosbarbero.authserver.model.converter.Map2JsonConverter;
import com.marcosbarbero.authserver.model.entity.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author Marcos Barbero
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "oauth_user_details")
public class User {

    @JsonIgnore
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 8, max = 100)
    @Column(unique = true, length = 100, nullable = false)
    private String username;

    @Size(min = 6, max = 50)
    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private String authorities;

    @Convert(converter = Map2JsonConverter.class)
    private Map<String, Object> additionalInformation;

    @Size(max = 10)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @PrePersist
    public void encryptPassword() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

}
