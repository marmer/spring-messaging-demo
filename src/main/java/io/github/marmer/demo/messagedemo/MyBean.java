package io.github.marmer.demo.messagedemo;

import com.github.javafaker.Faker;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

  private final JmsMessagingTemplate jmsTemplate;
  private final Faker faker = new Faker(new Random(42));

  public MyBean(final JmsMessagingTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;

    Executors.newFixedThreadPool(1)
        .submit(() -> sendDelayed());
  }

  @SneakyThrows
  private void sendDelayed() {
    System.out.println("Trying to send");

    jmsTemplate.convertAndSend("ChuckNorrisFactCreated",
        new ChuckNorrisFactsHolder(faker.chuckNorris().fact(), faker.superhero().name()),
        Map.of("related_god", faker.ancient().god()));

    jmsTemplate.convertAndSend("ChuckNorrisFactCreated",
        faker.chuckNorris().fact(),
        Map.of("related_god", faker.ancient().god()));

    System.out.println("Sent");
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ChuckNorrisFactsHolder {

    String someFact;
    String eyewitness;
  }

//  @JmsListener(destination = "ChuckNorrisFactCreated")
//  public void processMessage(final Message content,
//      @Headers final Map<String, Object> headers) {
//    System.out.println("Received Message: " + content);
//    System.out.println("Received Headers: " + headers.get("related_god"));
//  }

  @JmsListener(destination = "ChuckNorrisFactCreated")
  public void processMessage(final ChuckNorrisFactsHolder content,
      @Headers final Map<String, Object> headers) {
    System.out.println("Received Message: " + content);
    System.out.println("Received Headers: " + headers.get("related_god"));
  }

  @JmsListener(destination = "ChuckNorrisFactCreated")
  public void processMessage(final String content,
      @Headers final Map<String, Object> headers) {
    System.out.println("Received Message: " + content);
    System.out.println("Received Headers: " + headers.get("related_god"));
  }

//TODO marmer: sending objects as JSON
//TODO marmer: Automated Integrationtests
//TODO marmer: Dead Letter Queue and errorhandling
//TODO marmer: Different Accounts for Different Applications (or api keys)
//TODO marmer: Encryption
//TODO marmer: Calling the Management Queue

//TODO marmer: https://activemq.apache.org/components/artemis/documentation/2.0.0/pre-acknowledge.html
}
