package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

  private final TodoJpaRepository todoRepository;

  public TodoService(TodoJpaRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todo addTodo(Todo todo) {
    return todoRepository.save(todo);
  }

  public List<Todo> findAll() {
    return todoRepository.findAll();
  }

  public Optional<Todo> findById(Long id) {
    return todoRepository.findById(id);
  }

  public void delete(Todo todo) {
    todoRepository.delete(todo);
  }

  public long count() {
    return todoRepository.count();
  }
}
