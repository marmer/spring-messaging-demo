package io.github.marmer.demo.messagedemo.messaging;

import org.springframework.stereotype.Component;

@Component
public class ChuckNorrisCiteJMSPollClient {

//  private final JmsMessagingTemplate jmsTemplate;
//
//  public ChuckNorrisCiteJMSPollClient(final JmsMessagingTemplate jmsTemplate) {
//    this.jmsTemplate = jmsTemplate;
//  }

  public void pollFactAndConvertWithHeaders() {
//    final var message = jmsTemplate.receive("ChuckNorrisFactForManualListener");
//    System.out.println("manually polled message: " + message.getPayload());
//    System.out.println("manually polled headers: " + message.getHeaders().get("related_witness"));
  }

  public void pollFactAndConvertWithoutHeaders() {
//    final var message = jmsTemplate.receiveAndConvert(
//        "ChuckNorrisFactForManualListener",
//        ChuckNorrisFactsHolder.class);
//
//    System.out.println(
//        "manually polled Message: " + message); //The message goes "right" to the Dead Letter Queue
  }
}
