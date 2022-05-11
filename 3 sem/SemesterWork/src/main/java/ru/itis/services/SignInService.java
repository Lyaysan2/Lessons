package ru.itis.services;

import ru.itis.dto.SignInForm;
import ru.itis.dto.UserDto;

public interface SignInService {
    boolean signIn(SignInForm signInForm);
}
