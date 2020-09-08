package com.example.SeeLife.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
    
    @ElementCollection
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "note_id")) // choose the name of the DB table storing the List<>
    @JoinColumn(name = "note_id")            // name of the @Id column of this entity
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(value={CascadeType.ALL})
    private List<String> images;
    
    @ElementCollection
    @CollectionTable(name = "videos", joinColumns = @JoinColumn(name = "note_id")) // choose the name of the DB table storing the List<>
    @JoinColumn(name = "note_id")            // name of the @Id column of this entity
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(value={CascadeType.ALL})
    private List<String> videos;
    
    @ElementCollection
    @CollectionTable(name = "audios", joinColumns = @JoinColumn(name = "note_id")) // choose the name of the DB table storing the List<>
    @JoinColumn(name = "note_id")            // name of the @Id column of this entity
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(value={CascadeType.ALL})
    private List<String> audios;
    
    @ElementCollection
    @CollectionTable(name = "other_files", joinColumns = @JoinColumn(name = "note_id")) // choose the name of the DB table storing the List<>
    @JoinColumn(name = "note_id")            // name of the @Id column of this entity
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Cascade(value={CascadeType.ALL})
    private List<String> other_files;
    
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
        this.day.setNotesNumber(this.day.getNotesNumber() + 1);
        
        // initialize the storages of files.
        this.images = this.videos = this.audios = this.other_files = new ArrayList<String>();
    }
    
    @PreRemove
    public void onDelete() {
        this.day.setNotesNumber(this.day.getNotesNumber() - 1);
    }
    
    public String getFormattedLocalTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:m:s a");
        String text = this.localTime.format(formatter);
        
        return text;
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
    
    public List<String> getImages() {
        return this.images;
    }
    
    public List<String> getVideos() {
        return this.videos;
    }
    
    public List<String> getAudios() {
        return this.audios;
    }
    
    public List<String> getOtherFiles() {
        return this.other_files;
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
    
    public void setImages(List<String> images) {
        this.images = images;
    }
    
    public void setVideos(List<String> videos) {
        this.videos = videos;
    }
    
    public void setAudios(List<String> audios) {
        this.audios = audios;
    }
    
    public void setOtherFiles(List<String> other_files) {
        this.other_files = other_files;
    }
    
    public void setDay(Day day) {
        this.day = day;
    }
}
