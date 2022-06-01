package ru.itis.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.itis.security.userDetails.CustomUser;

import java.util.UUID;

@Component("userSecurity")
public class UserSecurity {

    public boolean hasUserId(Authentication authentication, UUID userId) {
        return ((CustomUser) authentication.getPrincipal()).getId().equals(userId);
    }
}
