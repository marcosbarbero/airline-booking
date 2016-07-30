package com.marcosbarbero.authserver.authentication;

import com.marcosbarbero.authserver.model.entity.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Custom user details class to handle extensions from database.
 *
 * @author Marcos Barbero
 */
public class UserDetails extends User implements Serializable {

    private static final long serialVersionUID = -4679192568029641583L;

    private Map<String, Object> extensions;
    private UserStatus status;

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserDetails(String username, String password, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Map<String, Object> extensions, UserStatus status) {
        super(username, password, true, true, true, accountNonLocked, authorities);
        this.extensions = extensions;
        this.status = status;
    }

    public Map<String, Object> getExtensions() {
        return extensions;
    }

    public void setExtensions(Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
