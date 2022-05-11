package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignUpForm;
import ru.itis.models.FileInfo;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final UsersRepository usersRepository;

    @Autowired
    public SignUpServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void signUp(SignUpForm form){

        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .age(form.getAge())
                .city(form.getCity())
                .email(form.getEmail())
                .password(form.getPassword())
                .avatarId(form.getAvatarId())
                .build();

        usersRepository.save(user);
    }
}
