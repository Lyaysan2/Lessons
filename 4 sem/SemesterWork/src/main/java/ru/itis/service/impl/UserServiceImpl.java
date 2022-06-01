package ru.itis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.UserRequest;
import ru.itis.dto.response.TokenCoupleResponse;
import ru.itis.dto.response.UserResponse;
import ru.itis.model.AccountEntity;
import ru.itis.repositories.UserRepository;
import ru.itis.service.UserService;
import ru.itis.service.jwt.JwtTokenService;
import ru.itis.utils.mapper.UserMapper;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    @Override
    public AccountEntity findUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<AccountEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserResponse loadUserByUsername(String subject) {
        return userMapper.toResponse(userRepository.findByEmail(subject).get());
    }

    @Override
    public TokenCoupleResponse login(UserRequest request) {
        return jwtTokenService.generateTokenCouple(userRepository.findByEmail(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getHashPassword()))
                .map(userMapper::toResponse)
                .orElseThrow(() -> new UnauthorizedException("Failed to log in: " + request.getEmail())));
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

}
