package com.marcosbarbero.authserver.helper;

import com.marcosbarbero.authserver.model.entity.User;
import com.marcosbarbero.authserver.model.entity.enums.UserStatus;

import java.util.Collections;

/**
 * Helper class to build objects for Unit tests.
 *
 * @author Marcos Barbero
 */
public class Given {

    public static User activeUser() {
        return new User(1, "username", "password", "ROLE_USER", Collections.emptyMap(), UserStatus.ACTIVE);
    }

    public static User blockedUser() {
        return new User(1, "username", "password", "ROLE_USER", Collections.emptyMap(), UserStatus.BLOCKED);
    }
}
