package io.github.marmer.demo.messagedemo;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.github.marmer.demo.messagedemo.messaging.ChuckNorrisFactsJMSSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(/*properties = {
    InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
    ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT + ".enabled=false"
}*/)
@Testcontainers
class ApplicationTest {

  @Container
  static GenericContainer<?> artemis = new GenericContainer<>(
      DockerImageName.parse("artemis-adoptopenjdk-11"))
      .withExposedPorts(61616)
      .withEnv("ARTEMIS_USER", "us")
      .withEnv("ARTEMIS_PASSWORD", "pw")
      .withEnv("ANONYMOUS_LOGIN", "false");

  @DynamicPropertySource
  static void conficureDynamicSpringProperties(final DynamicPropertyRegistry registry) {
    registry.add("spring.artemis.broker-url", () ->
        "tcp://" + artemis.getHost() + ":" + artemis.getMappedPort(61616));
  }

  @Autowired
  private JmsMessagingTemplate template;

  @Autowired
  private ChuckNorrisFactsJMSSender chuckNorrisFactsJMSSender;

  @Test
  void contextLoads() {
    chuckNorrisFactsJMSSender.sendChuckNorrisFact(new ChuckNorrisFactsHolder("fact", "wittness"));

    final Message<?> message = await()
        .atMost(2, SECONDS)
        .until(
            () ->
                template.receive("ChuckNorrisFactForManualListener"), is(notNullValue())
        );

    assertThat(message.getPayload(), hasProperty("someFact", equalTo("fact")));
  }

}
