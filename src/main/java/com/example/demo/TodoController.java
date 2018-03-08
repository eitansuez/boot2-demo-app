package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TodoController {

  private final TodoRepository todoRepository;

  public TodoController(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @GetMapping("/todos")
  public String fetchTodos(Model model) {

    List<TodoViewModel> todoViews = todoRepository.findAll()
        .stream()
        .map(TodoViewModel::new)
        .collect(Collectors.toList());

    model.addAttribute("todos", todoViews);

    return "todos"; // the name of the template to render
  }
}
