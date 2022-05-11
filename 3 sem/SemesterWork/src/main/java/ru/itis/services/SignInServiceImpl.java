package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignInForm;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    private final UsersRepository usersRepository;

    @Autowired
    public SignInServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean signIn(SignInForm signInForm) {
        Optional<User> userOptional = usersRepository.findByEmail(signInForm.getEmail());

        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();

        return user.getPassword().equals(signInForm.getPassword());
    }
}
