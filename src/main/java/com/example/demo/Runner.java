package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class Runner implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(Runner.class);

  private final HelloService helloService;
  private final TodoService todoService;

  public Runner(HelloService helloService, TodoService todoService) {
    this.helloService = helloService;
    this.todoService = todoService;
  }

  @Override
  public void run(String... args) {
    helloService.greet();

    if (todoService.count() == 0) {
      initializeTodos();
    }

    logger.debug("exiting run method..");
  }

  private void initializeTodos() {
    LocalDate nextWeek = LocalDate.now().plus(7, ChronoUnit.DAYS);

    todoService.addTodo(Todo.builder()
        .title("Shop")
        .description("Go shopping ahead of trip")
        .dueDate(nextWeek).build());
    todoService.addTodo(Todo.builder()
        .title("Pack")
        .description("Be sure to pack your things")
        .dueDate(nextWeek).build());
    todoService.addTodo(Todo.builder()
        .title("Drive")
        .description("Drive to the airport")
        .dueDate(nextWeek).build());
    todoService.addTodo(Todo.builder()
        .title("Fly")
        .description("Fly to some mysterious destination")
        .dueDate(nextWeek).build());
  }
}
