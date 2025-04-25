package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.entity.Task;
import springboot.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    
    // Home page
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Show Add Task Form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("task", new Task());
        return "add_task";
    }

    // Handle Add Task Form
    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/view";
    }

    // Show All Tasks
    @GetMapping("/view")
    public String viewAllTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "view_task";
    }

    // Show Edit Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "edit_task";
        } else {
            return "redirect:/view";
        }
    }

    // Handle Edit Submission
    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/view";
    }

    // Delete Task
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") int id) {
        taskRepository.deleteById(id);
        return "redirect:/view";
    }

    // Search by Title
    @GetMapping("/search")
    public String searchTasks(@RequestParam("keyword") String keyword, Model model) {
        List<Task> tasks = taskRepository.findByTitleContainingIgnoreCase(keyword);
        model.addAttribute("tasks", tasks);
        return "view_task";
    }
}
