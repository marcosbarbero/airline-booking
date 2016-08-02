package com.marcosbarbero.authserver.service;

import com.marcosbarbero.authserver.AuthServerApplication;
import com.marcosbarbero.authserver.helper.Given;
import com.marcosbarbero.authserver.model.entity.User;
import com.marcosbarbero.authserver.model.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

/**
 * @author Marcos Barbero
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerApplication.class)
public class UserDetailsServiceTest {

    @MockBean
    private UserRepository userRepository;

    private UserDetailsService userDetailsService;

    @Before
    public void setUp() {
        this.userDetailsService = new UserDetailsServiceImpl(this.userRepository);
    }

    private void assertion(final UserDetails userDetails, final User user) {
        Assert.notNull(userDetails);
        Assert.isTrue(userDetails.getUsername().equals(user.getUsername()));
        Assert.isTrue(userDetails.getPassword().equals(user.getPassword()));
        Assert.isTrue(userDetails.getAuthorities().containsAll(AuthorityUtils.createAuthorityList(user.getAuthorities())));
    }

    @Test
    public void testLoadUserByUsername_activeUser() {
        User user = Given.activeUser();
        given(this.userRepository.findByUsername(any(String.class))).willReturn(user);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername("username");
        assertion(userDetails, user);
        Assert.isTrue(userDetails.isAccountNonLocked());
    }

    @Test
    public void testLoadUserByUsername_blockedUser() {
        User user = Given.blockedUser();
        given(this.userRepository.findByUsername(any(String.class))).willReturn(user);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername("username");
        assertion(userDetails, user);
        Assert.isTrue(!userDetails.isAccountNonLocked());
    }

    @Test
    public void testLoadUserByUsername_userNotFound() {
        given(this.userRepository.findByUsername(any(String.class))).willReturn(null);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername("username");
        Assert.isTrue(userDetails == null);
    }
}
