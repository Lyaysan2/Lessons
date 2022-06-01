package ru.itis.security.userDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.itis.dto.response.UserResponse;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) {
        return loadUserDetails((UserResponse) preAuthenticatedAuthenticationToken.getPrincipal(),
                String.valueOf(preAuthenticatedAuthenticationToken.getCredentials()));
    }

    private UserDetails loadUserDetails(UserResponse userResponse, String token) {
        try {
            return Optional.ofNullable(userResponse)
                    .map(account -> {
                        List<SimpleGrantedAuthority> authorities = HttpRequestUtil.getAuthorities(account.getRoles());
                        return CustomUser.builder()
                                .id(account.getId())
                                .username(account.getEmail())
                                .fullName(account.getFirstName().concat(" ").concat(account.getLastName()))
                                .createDate(Instant.now())
                                .authorities(authorities)
                                .isAccountNonExpired(true)
                                .isCredentialsNonExpired(true)
                                .isAccountNonLocked(true)
                                .isEnabled(true)
                                .token(token)
                                .build();
                    })
                    .orElseThrow(() -> new UsernameNotFoundException("Unknown user by token " + token));
        } catch (Exception exception) {
            throw new AuthenticationHeaderException(exception.getMessage());
        }
    }

}
