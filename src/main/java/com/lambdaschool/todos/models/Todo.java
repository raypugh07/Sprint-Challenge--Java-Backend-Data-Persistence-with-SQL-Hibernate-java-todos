/*package com.lambdaschool.todos.models;

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


//@EnableTransactionManagement
//@Access(AccessType.PROPERTY)
@EnableTransactionManagement
@Access(value=AccessType.PROPERTY)
@Entity
@Table(name = "todos")

public class Todo extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long todoid;

    @Column(nullable = false)
    private String todo;

    @Column(name="datestarted")
    private String datestarted;
    @Column(name="datestarted1")
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datestarted);

    @Column(name = "completed")
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
    @Id
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

    public Boolean isCompleted() {
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
}*/

package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

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
