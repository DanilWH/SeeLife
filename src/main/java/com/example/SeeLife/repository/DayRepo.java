package com.example.SeeLife.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import com.example.SeeLife.model.Day;

public interface DayRepo extends CrudRepository<Day, Long> {
    
    Day findByLocalDate(LocalDate localDate);
}
