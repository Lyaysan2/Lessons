package ru.itis.services;

import ru.itis.model.Doctor;
import ru.itis.model.Patient;

public interface PatientService {
    Patient getById(Long id);
}
