package io.github.marmer.demo.messagedemo.messaging;

import io.github.marmer.demo.messagedemo.ChuckNorrisFactsHolder;
import java.util.Map;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChuckNorrisFactsJMSSender {

  private final JmsMessagingTemplate jmsTemplate;


  public ChuckNorrisFactsJMSSender(final JmsMessagingTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void sendChuckNorrisFact(final ChuckNorrisFactsHolder chuckNorrisFact) {
    jmsTemplate.convertAndSend("ChuckNorrisFactForAutomaticListener",
        chuckNorrisFact,
        Map.of("related_witness", chuckNorrisFact.getEyeWittness()));

    jmsTemplate.convertAndSend("ChuckNorrisFactForManualListener",
        chuckNorrisFact,
        Map.of("related_witness", chuckNorrisFact.getEyeWittness()));

    jmsTemplate.convertAndSend("ChuckNorrisFactForAutomatedErrorListener",
        chuckNorrisFact,
        Map.of("related_witness", chuckNorrisFact.getEyeWittness()));

    // TODO: marmer 25.10.2021 implement the related Listener
    jmsTemplate.convertAndSend("ChuckNorrisFactForManualErrorListener",
        chuckNorrisFact,
        Map.of("related_witness", chuckNorrisFact.getEyeWittness()));
  }
}
