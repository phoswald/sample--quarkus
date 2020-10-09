package com.github.phoswald.sample.task;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Dependent
public class TaskRepository {

    @Inject
	EntityManager em;

    public List<TaskEntity> selectAllTasks() {
        TypedQuery<TaskEntity> query = em.createNamedQuery(TaskEntity.SELECT_ALL, TaskEntity.class);
        query.setMaxResults(100);
        return query.getResultList();
    }

    public TaskEntity selectTaskById(String taskId) {
        return em.find(TaskEntity.class, taskId);
    }

    public void createTask(TaskEntity entity) {
        em.persist(entity);
    }

    public void deleteTask(TaskEntity entity) {
        em.remove(entity);
    }

    public void updateChanges() {
        em.flush();
    }
}
