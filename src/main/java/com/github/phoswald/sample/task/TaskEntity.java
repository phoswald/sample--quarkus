package com.github.phoswald.sample.task;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "task")
@NamedQueries({ //
        @NamedQuery(name = TaskEntity.SELECT_ALL, query = "select t from TaskEntity t order by t.timestamp desc") })
public class TaskEntity {

    static final String SELECT_ALL = "TaskEntity.Select";

    @Id
    @Column(name = "task_id")
    private String taskId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "done")
    private Boolean done;

    public String getTaskId() {
        return taskId;
    }

    public void setNewTaskId() {
        this.taskId = UUID.randomUUID().toString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getTimestamp() {
        return timestamp == null ? null : timestamp.toInstant();
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp == null ? null : Date.from(timestamp);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done == null ? false : done.booleanValue();
    }

    public void setDone(boolean done) {
        this.done = Boolean.valueOf(done);
    }
}
