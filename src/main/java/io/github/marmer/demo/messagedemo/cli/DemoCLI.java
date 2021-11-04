package io.github.marmer.demo.messagedemo.cli;

import io.github.marmer.demo.messagedemo.ChuckNorrisFactSource;
import io.github.marmer.demo.messagedemo.ChuckNorrisFactsHolder;
import io.github.marmer.demo.messagedemo.messaging.ChuckNorrisFactsJMSSender;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DemoCLI {

  private final ChuckNorrisFactsJMSSender chuckNorrisFactsJMSSender;
  private final ChuckNorrisFactSource chuckNorrisFactSource;

  public DemoCLI(
      final ChuckNorrisFactsJMSSender chuckNorrisFactsJMSSender,
      final ChuckNorrisFactSource chuckNorrisFactSource) {
    this.chuckNorrisFactsJMSSender = chuckNorrisFactsJMSSender;
    this.chuckNorrisFactSource = chuckNorrisFactSource;
    System.out.println("####### Welcome to the messaging demo Print help for help ########");
  }

  @ShellMethod("Adds a new fact to all queues/topics")
  public void addNew() {
    chuckNorrisFactsJMSSender.sendChuckNorrisFact(
        new ChuckNorrisFactsHolder(chuckNorrisFactSource.getFact(),
            chuckNorrisFactSource.getEyeWittness()));
  }

  @ShellMethod(value = "kills the application")
  public void kill() {
    System.exit(0);
  }


}
