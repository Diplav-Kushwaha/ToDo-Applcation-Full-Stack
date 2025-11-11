package com.diplav.ToDoApplication.controller;

import java.util.List;
import java.time.LocalDate;
import org.springframework.ui.Model;
import com.diplav.ToDoApplication.models.Task;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.diplav.ToDoApplication.services.TaskService;
import com.diplav.ToDoApplication.repository.TaskRepository;


@Controller
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }
    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("task", new Task()); // must match th:object="${task}"
        model.addAttribute("tasks", taskService.getAllTasks());
        return "home";
    }
    @PostMapping("/create")
    public String createTasks(@ModelAttribute("task") Task task){
        task.setDate(LocalDate.now());
        taskService.save(task);
        return "redirect:/";
    }
    @RequestMapping("/delete/{id}")
    public String deleteTasks(@PathVariable("id") Long id){
        taskService.deleteTask(id);
        return "redirect:/";
    }
    @GetMapping("/toggle/{id}")
    public String toggleTasks(@PathVariable("id") Long id){
        taskService.toggleTask(id);
        return "redirect:/";
    }
    @GetMapping("/update/form/{id}")
    public String updatingForm(@PathVariable("id") Long id, Model model) {
        Task task= taskRepository.findById(id).orElse(null);
        model.addAttribute("task", task);
        return "updateTask";
    }
    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable("id") Long id, @ModelAttribute("task") Task task) {
        taskService.updateTask(id, task);
        return "redirect:/";
    }
    @GetMapping("/search")
    public String searchTask(Model model){
        model.addAttribute("tasks", taskRepository.findAll());
        return "checkTask";
    }
//    @GetMapping("/doSearch")
//    public String doSearch(@RequestParam("title") String title, Model model) {
//        List<Task> taskList = taskService.findByTitle(title);
//        model.addAttribute("tasks", taskList);
//        return "searchTask";
//    }

}
