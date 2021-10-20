package io.github.marmer.demo.messagedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
