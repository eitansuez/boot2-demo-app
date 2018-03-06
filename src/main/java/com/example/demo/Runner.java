package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(Runner.class);

  private final HelloService helloService;

  public Runner(HelloService helloService) {
    this.helloService = helloService;
  }

  @Override
  public void run(String... args) {
    helloService.greet();
    logger.debug("exiting run method..");
  }
}
