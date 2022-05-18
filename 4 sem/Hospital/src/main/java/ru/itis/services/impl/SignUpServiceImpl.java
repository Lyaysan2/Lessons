package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dto.request.SignUpForm;
import ru.itis.exceptions.ErrorEntity;
import ru.itis.exceptions.ValidationException;
import ru.itis.mapper.PatientMapper;
import ru.itis.model.Patient;
import ru.itis.repositories.PatientRepository;
import ru.itis.services.SignUpService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {
    private final PatientRepository accountsRepository;
    private final PatientMapper accountsMapper;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm signUpForm) {
        Set<ConstraintViolation<SignUpForm>> violations = validator.validate(signUpForm);
        if(!violations.isEmpty()) {
            throw new ValidationException(violations.stream().findFirst().get().getMessage());
        }

        if (accountsRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            throw new ValidationException(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }

        signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        Patient account = accountsMapper.toPatient(signUpForm);
        accountsRepository.save(account);
    }

}
