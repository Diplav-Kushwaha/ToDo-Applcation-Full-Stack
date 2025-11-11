package com.diplav.ToDoApplication.services;

import com.diplav.ToDoApplication.models.Task;
import com.diplav.ToDoApplication.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public void save(Task task) {
        taskRepository.save(task);
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(!task.isCompleted()); // ✅ toggle true/false
            taskRepository.save(task);
        }
    }
    public void updateTask(Long id, Task task) {
        Task existing = taskRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(task.getTitle());
            existing.setDescription(task.getDescription());
            existing.setDate(task.getDate());

            taskRepository.save(existing);
        }
    }
    public List<Task> findByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    public Object getAllTasks() {
        return  taskRepository.findAll();
    }
}
