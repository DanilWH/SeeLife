package com.example.SeeLife.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.SeeLife.model.Note;

public interface NoteRepo extends CrudRepository<Note, Long> {
    
    Iterable<Note> findByDayId(Long dayId);
    List<Note> findByDayIdOrderByLocalTime(Long dayId);

}
