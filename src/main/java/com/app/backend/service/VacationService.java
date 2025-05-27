package com.app.backend.service;

import com.app.backend.model.User;
import com.app.backend.model.Vacation;
import com.app.backend.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private UserService userService;

    public List<Vacation> getVacationByUserId(Integer id) {
        User user = userService.findById(id);
        return user.getVacations();
    }

    public Vacation save(Vacation vacation) {
        return vacationRepository.save(vacation);
    }

    public Vacation changeAcceptation(Integer id, Boolean accept) {
        Optional<Vacation> optionalVacation = vacationRepository.findById(id);

        if (optionalVacation.isPresent()) {
            Vacation vacation = optionalVacation.get();
            vacation.setAccepted(accept);
            return vacationRepository.save(vacation);
        }
        return null;
    }
}
