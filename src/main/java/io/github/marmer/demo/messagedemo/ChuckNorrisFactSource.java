package io.github.marmer.demo.messagedemo;

import com.github.javafaker.Faker;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class ChuckNorrisFactSource {

  private final Faker faker = new Faker(new Random(42));


  public String getFact() {
    return faker.chuckNorris().fact();
  }

  public String getEyeWittness() {
    return faker.ancient().god();
  }
}
