package com.diplav.ToDoApplication.controller;

import java.util.List;
import org.springframework.ui.Model;
import com.diplav.ToDoApplication.models.Task;
import org.springframework.stereotype.Controller;
import com.diplav.ToDoApplication.services.TaskService;
import org.springframework.web.bind.annotation.*;


@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/")
    public String getTasks(Model model){
        List<Task> tasks=taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
    @PostMapping("/")
    public String createTasks(@RequestParam String title){
        taskService.createTask(title);
        return "redirect:/";
    }
    @GetMapping("/{id}/delete")
    public String deleteTasks(@PathVariable("id") Long id){
        taskService.deleteTask(id);
        return "redirect:/";
    }
    @GetMapping("/{id}/toggle")
    public String toggleTasks(@PathVariable("id") Long id){
        taskService.toggleTask(id);
        return "redirect:/";
    }
}
