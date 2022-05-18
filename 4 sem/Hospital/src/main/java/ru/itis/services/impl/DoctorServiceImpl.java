package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.model.Doctor;
import ru.itis.repositories.DoctorRepository;
import ru.itis.services.DoctorService;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Doctor getById(Long id) {
        return doctorRepository.getById(id);
    }
}
