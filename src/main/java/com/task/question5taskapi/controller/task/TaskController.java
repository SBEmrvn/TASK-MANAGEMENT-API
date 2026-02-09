package com.task.question5taskapi.controller.task;

import com.task.question5taskapi.model.task.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Task Management API
 * Handles all HTTP requests for task operations
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    // In-memory storage for tasks
    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    /**
     * Constructor - Initialize with sample tasks
     */
    public TaskController() {
        tasks.add(new Task(nextId++, "Complete Spring Boot Assignment", "Finish all 5 REST API questions", false, "HIGH", "2026-02-10"));
        tasks.add(new Task(nextId++, "Review Java Concepts", "Study OOP principles and collections", false, "MEDIUM", "2026-02-12"));
        tasks.add(new Task(nextId++, "Setup Development Environment", "Install VS Code, Postman, and Java", true, "HIGH", "2026-02-05"));
        tasks.add(new Task(nextId++, "Learn Git Commands", "Practice git add, commit, push, and branch", false, "MEDIUM", "2026-02-15"));
        tasks.add(new Task(nextId++, "Read Spring Documentation", "Go through Spring Boot official docs", false, "LOW", "2026-02-20"));
        tasks.add(new Task(nextId++, "Practice REST API Testing", "Test all endpoints using Postman", true, "MEDIUM", "2026-02-08"));
    }

    /**
     * GET /api/tasks
     * Get all tasks
     * Status: 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(tasks);
    }

    /**
     * GET /api/tasks/{taskId}
     * Get task by ID
     * Status: 200 OK if found, 404 NOT FOUND if not found
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getTaskId().equals(taskId))
                .findFirst();
        
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * GET /api/tasks/status?completed={true/false}
     * Get tasks by completion status
     * Status: 200 OK
     */
    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> matchingTasks = tasks.stream()
                .filter(t -> t.isCompleted() == completed)
                .toList();
        
        return ResponseEntity.ok(matchingTasks);
    }

    /**
     * GET /api/tasks/priority/{priority}
     * Get tasks by priority level
     * Status: 200 OK
     */
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> matchingTasks = tasks.stream()
                .filter(t -> t.getPriority().equalsIgnoreCase(priority))
                .toList();
        
        return ResponseEntity.ok(matchingTasks);
    }

    /**
     * POST /api/tasks
     * Create new task
     * Status: 201 CREATED
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setTaskId(nextId++);
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    /**
     * PUT /api/tasks/{taskId}
     * Update task
     * Status: 200 OK if updated, 404 NOT FOUND if not found
     */
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Optional<Task> existingTask = tasks.stream()
                .filter(t -> t.getTaskId().equals(taskId))
                .findFirst();
        
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            // Update all fields except taskId
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
            task.setPriority(updatedTask.getPriority());
            task.setDueDate(updatedTask.getDueDate());
            
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * PATCH /api/tasks/{taskId}/complete
     * Mark task as completed
     * Status: 200 OK if updated, 404 NOT FOUND if not found
     */
    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long taskId) {
        Optional<Task> existingTask = tasks.stream()
                .filter(t -> t.getTaskId().equals(taskId))
                .findFirst();
        
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setCompleted(true);
            
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * DELETE /api/tasks/{taskId}
     * Delete task
     * Status: 204 NO CONTENT if deleted, 404 NOT FOUND if not found
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        boolean removed = tasks.removeIf(t -> t.getTaskId().equals(taskId));
        
        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}