package io.github.marmer.demo.messagedemo.messaging;

import io.github.marmer.demo.messagedemo.ChuckNorrisFactsHolder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ChuckNorrisCiteJMSListener {

  @RabbitListener(queues = "myQueue")
  public void listenMyQueue(final ChuckNorrisFactsHolder in) {
    System.out.println("Message read from myQueue : " + in);
  }

  @RabbitListener(queues = "myQueue2")
  public void listenMyQueue2(final ChuckNorrisFactsHolder in) {
    System.out.println("Message read from myQueue2 : " + in);
  }

  //  @JmsListener(destination = "ChuckNorrisFactForAutomaticListener")
  public void processMessage(final ChuckNorrisFactsHolder content
      //use @Headers for all headers
      /*@Header("related_witness") final String eyeWittness*/) {
    System.out.println("Automatically received message: " + content);
//  System.out.println("Automatically received headers: " + eyeWittness);
  }

  //  @JmsListener(destination = "ChuckNorrisFactForAutomatedErrorListener")
  public void processMessageWithError(final ChuckNorrisFactsHolder content
      //use @Headers for all headers
      /*@Header("related_witness") final String eyeWittness*/) {
    throw new RuntimeException("Something really 'unexpected' happend");
  }

  //  @JmsListener(destination = "ChuckNorrisFactForAutomatedError2Listener")
  public void processMessageWithError2(final ChuckNorrisFactsHolder content
      //use @Headers for all headers
      /*@Header("related_witness") final String eyeWittness*/) {
    throw new RuntimeException("Something really 'unexpected' happend");
  }

  //  @JmsListener(destination = "DLQ", selector = "_AMQ_ORIG_ADDRESS = 'ChuckNorrisFactForAutomatedError2Listener'")
  public void processDeadLetterContent(final ChuckNorrisFactsHolder content
      //use @Headers for all headers
      /*@Header("related_witness") final String eyeWittness*/) {
    System.out.println("Read Message from Dead Letter Queue: " + content);
  }
}
