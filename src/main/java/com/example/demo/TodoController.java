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

  private final List<Todo> todoList = new ArrayList<>();

  public TodoController() {
    LocalDate nextWeek = LocalDate.now().plus(7, ChronoUnit.DAYS);

    todoList.add(Todo.builder()
        .title("Shop")
        .description("Go shopping ahead of trip")
        .dueDate(nextWeek).build());
    todoList.add(Todo.builder()
        .title("Pack")
        .description("Be sure to pack your things")
        .dueDate(nextWeek).build());
    todoList.add(Todo.builder()
        .title("Drive")
        .description("Drive to the airport")
        .dueDate(nextWeek).build());
    todoList.add(Todo.builder()
        .title("Fly")
        .description("Fly to some mysterious destination")
        .dueDate(nextWeek).build());
  }

  @GetMapping("/todos")
  public String fetchTodos(Model model) {

    List<TodoViewModel> todoViews = todoList
        .stream()
        .map(TodoViewModel::new)
        .collect(Collectors.toList());

    model.addAttribute("todos", todoViews);

    return "todos"; // the name of the template to render
  }
}
