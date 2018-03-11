package com.example.demo;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

  private final TodoJpaRepository todoRepository;
  private final Timer timer;

  public TodoService(TodoJpaRepository todoRepository,
                     MeterRegistry meterRegistry) {
    this.todoRepository = todoRepository;
    this.timer = meterRegistry.timer("todos.added");
  }

  public Todo addTodo(Todo todo) throws Exception {
    return timer.recordCallable(() -> todoRepository.save(todo));
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
