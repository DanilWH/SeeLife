package com.example.SeeLife.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.SeeLife.model.Day;

public interface DayRepo extends CrudRepository<Day, Long> {
}
