package io.github.marmer.demo.messagedemo;

import javax.jms.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class Application {

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

  // Only required due to defining myFactory in  receiver
  @Bean
  public JmsListenerContainerFactory<?> myFactory(
      final ConnectionFactory connectionFactory,
      final DefaultJmsListenerContainerFactoryConfigurer configurer) {
    final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    return factory;
  }

  @Bean
  public MessageConverter jacksonJmsMessageConverter() {
    final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName(
        "_type"); //Header name used to store the qualified typename of the type for deserialization

//    Suggested Convention for type names in DDD environment: <domain>.<aggregate>.<version>.<Eventname>Event
//    e.g. bestaetigungen.testbericht.v1.TestberichtFuerBestaetigungVorgemerktEvent
    return converter;
  }
}
