package com.example.SeeLife.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private LocalTime localTime;
    
    @Column(columnDefinition="TEXT")
    private String text;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="day_id")
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Day day;
    
    public Note() {
    }
    
    public Note(String text, Day day) {
        this.localTime = LocalTime.now();
        this.text = text;
        this.day = day;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public LocalTime getLocalTime() {
        return this.localTime; 
    }
    
    public String getText() {
        return this.text;
    }
    
    public Day getDay() {
        return this.day;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setDay(Day day) {
        this.day = day;
    }
}
