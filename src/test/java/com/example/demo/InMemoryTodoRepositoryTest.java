package com.example.demo;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryTodoRepositoryTest {

  private InMemoryTodoRepository repository;
  private Todo todo;

  @Before
  public void setup() {
    repository = new InMemoryTodoRepository();

    todo = Todo.builder()
        .title("My Todo")
        .description("it's urgent")
        .dueDate(LocalDate.now())
        .build();

  }

  @Test
  public void shouldBeInitiallyEmpty() {
    assertThat(repository.findAll()).isEmpty();
    assertThat(repository.count()).isEqualTo(0);
  }

  @Test
  public void shouldAddAnEntry() {
    Todo savedTodo = repository.save(todo);
    assertThat(savedTodo.getId()).isNotNull();
    assertThat(savedTodo.getTitle()).isEqualTo(todo.getTitle());
    assertThat(repository.count()).isEqualTo(1);
    assertThat(repository.findById(savedTodo.getId()).isPresent()).isTrue();
  }

  @Test
  public void shouldNotFindTodoWithAbsentId() {
    assertThat(repository.findById(123L).isPresent()).isFalse();
  }

  @Test
  public void shouldRemoveAnEntry() {
    Todo savedTodo = repository.save(todo);
    repository.delete(savedTodo);
    assertThat(repository.findAll()).isEmpty();
  }

  @Test
  public void shouldHandleDeleteATodoWithNoId() {
    repository.delete(todo);
    assertThat(repository.findAll()).isEmpty();
  }

  @Test
  public void shouldHandleDeleteATodoWithIdNotPresent() {
    Todo todoWithId = todo.toBuilder().id(17L).build();
    repository.delete(todoWithId);
    assertThat(repository.findAll()).isEmpty();
  }

  @Test
  public void shouldReplaceTodoWithSameId() {
    Todo savedTodo = repository.save(todo);

    Long id = savedTodo.getId();

    Todo anotherTodo = Todo.builder()
        .id(id)
        .title("Another Todo")
        .description("it's not as urgent")
        .dueDate(LocalDate.now().plus(1, ChronoUnit.DAYS))
        .build();

    repository.save(anotherTodo);

    assertThat(repository.count()).isEqualTo(1);
    assertThat(repository.findById(id).isPresent()).isTrue();
    assertThat(repository.findById(id).get().getTitle()).isEqualTo(anotherTodo.getTitle());
    assertThat(repository.findById(id).get().getDescription()).isEqualTo(anotherTodo.getDescription());
  }

  @Test
  public void shouldHoldMultipleEntries() {
    int n = 100;
    for (int i = 0; i < n; i++) {
      repository.save(Todo.builder()
          .title("title" + i)
          .description("description" + i)
          .dueDate(LocalDate.now()).build()
      );
    }
    assertThat(repository.count()).isEqualTo(n);
  }

}