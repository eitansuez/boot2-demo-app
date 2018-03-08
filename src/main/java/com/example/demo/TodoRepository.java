package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
  Todo save(Todo todo);

  void delete(Todo todo);

  List<Todo> findAll();

  long count();

  Optional<Todo> findById(Long id);
}
