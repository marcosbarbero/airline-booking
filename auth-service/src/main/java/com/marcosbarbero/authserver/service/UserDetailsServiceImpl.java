package com.marcosbarbero.authserver.service;

import com.marcosbarbero.authserver.config.oauth2.UserDetails;
import com.marcosbarbero.authserver.model.entity.User;
import com.marcosbarbero.authserver.model.entity.enums.UserStatus;
import com.marcosbarbero.authserver.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Custom implementation for UserDetailsService.
 *
 * @author Marcos Barbero
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
            userDetails = new UserDetails(user.getUsername(), user.getPassword(), isActive(user.getStatus()),
                    grantedAuthorities, user.getAdditionalInformation(), user.getStatus());
        }
        return userDetails;
    }

    private boolean isActive(UserStatus status) {
        return status == UserStatus.ACTIVE;
    }
}
