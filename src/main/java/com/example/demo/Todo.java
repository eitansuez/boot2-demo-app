package com.example.demo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Todo {
  private final String title, description;
  private final LocalDate dueDate;
}
