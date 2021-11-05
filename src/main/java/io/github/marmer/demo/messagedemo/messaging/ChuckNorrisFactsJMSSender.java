package io.github.marmer.demo.messagedemo.messaging;

import io.github.marmer.demo.messagedemo.ChuckNorrisFactsHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChuckNorrisFactsJMSSender {

  private final RabbitTemplate rabbitTemplate;

  public ChuckNorrisFactsJMSSender(
      final RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendChuckNorrisFact(final ChuckNorrisFactsHolder chuckNorrisFact) {
    rabbitTemplate.convertAndSend("ChuckNorrisFactsRoute.1", chuckNorrisFact, message -> {
      message.getMessageProperties().setHeader("related_witness", chuckNorrisFact.getEyeWittness());
      return message;
    });
    rabbitTemplate.convertAndSend("ChuckNorrisFactsRoute.2", chuckNorrisFact, message -> {
      message.getMessageProperties().setHeader("related_witness", chuckNorrisFact.getEyeWittness());
      return message;
    });
  }
}
