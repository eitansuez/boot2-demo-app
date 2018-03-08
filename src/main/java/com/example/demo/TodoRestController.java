package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoRestController {
  private final TodoRepository todoRepository;

  public TodoRestController(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @GetMapping
  List<Todo> getTodos() {
    return todoRepository.findAll();
  }

  @GetMapping("{id}")
  ResponseEntity<Todo> findById(@PathVariable Long id) {
    Optional<Todo> result = todoRepository.findById(id);
    if (result.isPresent()) {
      return ResponseEntity.ok(result.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
    Todo savedTodo = todoRepository.save(todo);
    return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
  }

  @DeleteMapping("{id}")
  ResponseEntity deleteTodo(@PathVariable("id") Long id) {
    Optional<Todo> result = todoRepository.findById(id);
    if (result.isPresent()) {
      todoRepository.delete(result.get());
    }
    return ResponseEntity.noContent().build();
  }

}
