package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public Map sayHello() {
    Map<String, String> map = new HashMap<>();
    map.put("message", "Hello world");
    map.put("color", "blue");
    return map;
  }

  @GetMapping("/hello/{who}")
  public Map sayHello(@PathVariable("who") String who) {
    Map<String, String> map = new HashMap<>();

    String message = String.format("Hello %s", who);
    map.put("message", message);

    return map;
  }
}
