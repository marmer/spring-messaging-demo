package io.github.marmer.demo.messagedemo.messaging;

import io.github.marmer.demo.messagedemo.ChuckNorrisFactsHolder;
import org.apache.activemq.artemis.jms.client.ActiveMQDestination;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChuckNorrisCiteJMSPollClient {

  private final JmsMessagingTemplate jmsTemplate;

  public ChuckNorrisCiteJMSPollClient(final JmsMessagingTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void pollFactAndConvertWithHeaders() {
    final var message = jmsTemplate.receive("ChuckNorrisFactForManualListener");
    System.out.println("manually polled message: " + message.getPayload());
    System.out.println("manually polled headers: " + message.getHeaders().get("related_witness"));
  }

  public void pollFactAndConvertWithoutHeaders() {
    final var message = jmsTemplate.convertSendAndReceive(
        ActiveMQDestination.createQueue("ChuckNorrisFactForManualListener"),
        ChuckNorrisFactsHolder.class);

    System.out.println("manually polled Message: " + message);
  }
}
