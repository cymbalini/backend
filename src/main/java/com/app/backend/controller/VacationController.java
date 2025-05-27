package com.app.backend.controller;

import com.app.backend.model.Vacation;
import com.app.backend.repository.VacationRepository;
import com.app.backend.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VacationController {

    @Autowired
    private VacationService vacationService;

    @Autowired
    private VacationRepository vacationRepository;

    @GetMapping("/getvacation/{id}")
    public List<Vacation> getUserVacation(@PathVariable Integer id) {
        return vacationService.getVacationByUserId(id);
    }
    @PutMapping("/updatevacationstatus/{id}")
    public Vacation changeVacationStatus(
            @PathVariable Integer id,
            @RequestBody Boolean accept
    ) {
        return vacationService.changeAcceptation(id, accept);
    }
    @PostMapping("/savevacation")
    public Vacation save(@RequestBody Vacation vacation) {return vacationService.save(vacation);}
    @GetMapping("/allvacations")
    public List<Vacation> getAllVacationRequests() {
        return vacationRepository.findAll();
    }

}
