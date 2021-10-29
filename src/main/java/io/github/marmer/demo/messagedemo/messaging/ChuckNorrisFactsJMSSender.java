package io.github.marmer.demo.messagedemo.messaging;

import io.github.marmer.demo.messagedemo.ChuckNorrisFactsHolder;
import java.util.Map;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChuckNorrisFactsJMSSender {

  private final RabbitMessagingTemplate rabbitTemplate;

  public ChuckNorrisFactsJMSSender(
      final RabbitMessagingTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendChuckNorrisFact(final ChuckNorrisFactsHolder chuckNorrisFact) {
//    rabbitTemplate.convertAndSend(chuckNorrisFact);
    rabbitTemplate.convertAndSend("myQueue", chuckNorrisFact);
//    rabbitTemplate.convertAndSend("myExchange", "myQueue", chuckNorrisFact);

    rabbitTemplate.convertAndSend("myQueue",
        chuckNorrisFact,
        Map.of("related_witness", chuckNorrisFact.getEyeWittness()));

    rabbitTemplate.convertAndSend("myQueue2",
        chuckNorrisFact,
        Map.of("related_witness", chuckNorrisFact.getEyeWittness()));
//
//    jmsTemplate.convertAndSend("ChuckNorrisFactForManualListener",
//        chuckNorrisFact,
//        Map.of("related_witness", chuckNorrisFact.getEyeWittness()));
//
//    jmsTemplate.convertAndSend("ChuckNorrisFactForAutomatedErrorListener",
//        chuckNorrisFact,
//        Map.of("related_witness", chuckNorrisFact.getEyeWittness()));
//
//    jmsTemplate.convertAndSend("ChuckNorrisFactForAutomatedError2Listener",
//        chuckNorrisFact,
//        Map.of("related_witness", chuckNorrisFact.getEyeWittness()));
  }
}
