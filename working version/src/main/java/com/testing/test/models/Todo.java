package com.testing.test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "todo")
public class Todo extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="todoid")
    private long todoid;

    @Column(nullable = false)
    private String todo;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name="datestarted")
    private String datestarted;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties({"todos", "hibernateLazyInitializer"})
    private User user;

    public Todo()
    {
    }

    public Todo(String todo,String datestarted,Boolean completed, User user)
    {
        this.todo = todo;
        this.datestarted=datestarted;
        this.completed=completed;
        this.user = user;

    }


    public long getTodoid()
    {
        return todoid;
    }

    public void setTodoid(long todoid)
    {
        this.todoid = todoid;
    }

    public String getTodo()
    {
        return todo;
    }

    public void setTodo(String quote)
    {
        this.todo = quote;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getDatestarted() {
        return datestarted;
    }

    public void setDatestarted(String datestarted) {
        this.datestarted = datestarted;
    }
}