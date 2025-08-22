package com.diplav.ToDoApplication.repository;

import com.diplav.ToDoApplication.models.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
