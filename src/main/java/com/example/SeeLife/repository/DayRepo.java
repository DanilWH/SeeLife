package com.example.SeeLife.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.SeeLife.model.Day;

public interface DayRepo extends CrudRepository<Day, Long> {
    
    List<Day> findByOwnerId(Long ownerId);
    List<Day> findByOwnerIdOrderByLocalDateDesc(Long ownerId);
    Day findByLocalDateAndOwnerId(LocalDate localDate, Long ownerId);
    List<Day> findByLocalDateBetweenAndOwnerId(LocalDate startDate, LocalDate endDate, Long ownerId);
    List<Day> findByLocalDateGreaterThanEqualAndOwnerId(LocalDate startDate, Long ownerId);
    List<Day> findByLocalDateLessThanEqualAndOwnerId(LocalDate endDate, Long ownerId);
    
}
