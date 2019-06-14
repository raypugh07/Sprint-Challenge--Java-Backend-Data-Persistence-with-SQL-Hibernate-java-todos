package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
//@EnableTransactionManagement
//@Access(AccessType.PROPERTY)
@Table(name = "todos")

public class Todo extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long todoid;

    @Column(nullable = false)
    private String todo;

    private String datestarted;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datestarted);


    private Boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties({"todos", "hibernateLazyInitializer"})
    @NotNull
    private User user;

    public Todo() {
    }

    public Todo(String todo, String datestarted, Boolean completed, User user) {
        this.todo = todo;
        this.datestarted = datestarted;
        this.completed = completed;
        this.user = user;
        this.simpleDateFormat = simpleDateFormat;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String description) {
        this.todo = description;
    }

    public String getDatestarted() {
        return datestarted;
    }

    public void setDatestarted(String datestarted) {
        this.datestarted = datestarted;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
