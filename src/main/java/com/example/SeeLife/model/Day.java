package com.example.SeeLife.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Day {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private LocalDate localDate;
    
    @Size(max=50)
    private String title;
    
    private Integer notesNumber;
    
    public Long getId() {
        return this.id;
    }
    
    public LocalDate getLocalDate() {
        return this.localDate;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public Integer getNotesNumber() {
        return this.notesNumber;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setNotesNumber(Integer notesNumber) {
        this.notesNumber = notesNumber;
    }
}
