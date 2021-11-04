package io.github.marmer.demo.messagedemo.messaging;

import io.github.marmer.demo.messagedemo.ChuckNorrisFactsHolder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class ChuckNorrisCiteJMSListener {

  @RabbitListener(queues = "ChuckNorrisFactsQueue1")
  public void listenChuckNorrisFactsQueue1(final ChuckNorrisFactsHolder in,
      @Header("related_witness") final String relatedWittness) {
    System.out.println("Message read from ChuckNorrisFactsQueue1 : " + in);
    System.out.println("Message read from ChuckNorrisFactsQueue1 wittness: " + relatedWittness);
  }

  @RabbitListener(queues = "ChuckNorrisFactsQueue2")
  public void listenChuckNorrisFactsQueue2(final ChuckNorrisFactsHolder in,
      @Header("related_witness") final String relatedWittness) {
    System.out.println("Message read from ChuckNorrisFactsQueue2 : " + in);
    System.out.println("Message read from ChuckNorrisFactsQueue2 wittness: " + relatedWittness);
  }
}
