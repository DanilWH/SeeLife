package com.example.SeeLife.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Day {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private LocalDate localDate;
    
    @Size(max=50)
    private String title;
    
    private Integer notesNumber;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    @OnDelete(action=OnDeleteAction.CASCADE)
    private User owner;
    
    public Day() {
    }
    
    public Day(String title, User owner) {
        this.title = title;
        this.owner = owner;
        this.localDate = LocalDate.now();
        this.notesNumber = 0;
    }
    
    public String getFormattedLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM d, uuuu");
        String text = this.localDate.format(formatter);
        
        return text;
    }
    
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
    
    public User getOwner() {
        return this.owner;
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
    
    public void setOwner(User owner) {
        this.owner = owner;
    }
}
