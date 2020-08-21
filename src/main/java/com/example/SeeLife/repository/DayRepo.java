package com.example.SeeLife.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.SeeLife.model.Day;

public interface DayRepo extends CrudRepository<Day, Long> {
    
    List<Day> findByOwnerId(Long owner_id);
    Day findByLocalDateAndOwnerId(LocalDate local_date, Long owner_id);
}
