package com.example.demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MemoryHealthIndicator implements HealthIndicator {

  @Override
  public Health health() {
    Runtime runtime = Runtime.getRuntime();
    long freeMemory = runtime.freeMemory();
    long totalMemory = runtime.totalMemory();
    double percentFree = 100 * (freeMemory / (double) totalMemory);

    Health.Builder builder = (percentFree < 10) ? Health.down() : Health.up();
    return builder.withDetail("total", totalMemory)
        .withDetail("free", freeMemory)
        .withDetail("percentFree", percentFree)
        .build();
  }
}
