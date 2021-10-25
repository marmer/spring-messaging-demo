package io.github.marmer.demo.messagedemo.messaging;

import io.github.marmer.demo.messagedemo.ChuckNorrisFactsHolder;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class ChuckNorrisCiteJMSListener {

  @JmsListener(destination = "ChuckNorrisFactForAutomaticListener")
  public void processMessage(final ChuckNorrisFactsHolder content,
      //use @Headers for all headers
      @Header("related_witness") final String eyeWittness) {
    System.out.println("Automatically received message: " + content);
    System.out.println("Automatically received headers: " + eyeWittness);
  }

  @JmsListener(destination = "ChuckNorrisFactForAutomatedErrorListener")
  public void processMessageWithError(final ChuckNorrisFactsHolder content,
      //use @Headers for all headers
      @Header("related_witness") final String eyeWittness) {
    throw new RuntimeException("Something really 'unexpected' happend");
  }

  @JmsListener(destination = "ChuckNorrisFactForAutomatedError2Listener")
  public void processMessageWithError2(final ChuckNorrisFactsHolder content,
      //use @Headers for all headers
      @Header("related_witness") final String eyeWittness) {
    throw new RuntimeException("Something really 'unexpected' happend");
  }

  @JmsListener(destination = "DLQ", selector = "_AMQ_ORIG_ADDRESS = 'ChuckNorrisFactForAutomatedError2Listener'")
  public void processDeadLetterContent(final ChuckNorrisFactsHolder content,
      //use @Headers for all headers
      @Header("related_witness") final String eyeWittness) {
    System.out.println("Read Message from Dead Letter Queue: " + content);
  }
}
