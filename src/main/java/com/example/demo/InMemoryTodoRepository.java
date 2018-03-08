package com.example.demo;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTodoRepository implements TodoRepository {

  private Map<Long, Todo> map = new HashMap<>();
  private AtomicLong sequence = new AtomicLong();

  @Override
  public Todo save(Todo todo) {
    if (todo.getId() == null) {
      Long id = sequence.incrementAndGet();
      Todo savedTodo = todo.toBuilder().id(id).build();
      map.put(id, savedTodo);
      return savedTodo;
    }

    return map.put(todo.getId(), todo);
  }

  @Override
  public void delete(Todo todo) {
    map.remove(todo.getId());
  }

  @Override
  public List<Todo> findAll() {
    return new ArrayList<>(map.values());
  }

  @Override
  public long count() {
    return map.size();
  }

  @Override
  public Optional<Todo> findById(Long id) {
    if (map.containsKey(id)) {
      return Optional.of(map.get(id));
    }
    return Optional.empty();
  }
}
