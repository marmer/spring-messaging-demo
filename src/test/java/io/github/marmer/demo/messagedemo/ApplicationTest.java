package io.github.marmer.demo.messagedemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Testcontainers
class ApplicationTest {

//  @Container
//  static GenericContainer<?> artemis = new GenericContainer<>(
//      DockerImageName.parse("artemis-adoptopenjdk-11"))
//      .withExposedPorts(61616)
//      .withEnv("ARTEMIS_USER", "us")
//      .withEnv("ARTEMIS_PASSWORD", "pw")
//      .withEnv("ANONYMOUS_LOGIN", "false");
//
//  @DynamicPropertySource
//  static void conficureDynamicSpringProperties(final DynamicPropertyRegistry registry) {
//    registry.add("spring.artemis.broker-url", () ->
//        "tcp://" + artemis.getHost() + ":" + artemis.getMappedPort(61616));
//  }
//
//  @Autowired
//  private JmsMessagingTemplate template;
//
//  @Autowired
//  private ChuckNorrisFactsJMSSender chuckNorrisFactsJMSSender;

  @Test
  void contextLoads() {
//    chuckNorrisFactsJMSSender.sendChuckNorrisFact(new ChuckNorrisFactsHolder("fact", "wittness"));
//
//    final Message<?> message = await()
//        .atMost(2, SECONDS)
//        .until(
//            () ->
//                template.receive("ChuckNorrisFactForManualListener"), is(notNullValue())
//        );
//
//    assertThat(message.getPayload(), hasProperty("someFact", equalTo("fact")));
  }

}
