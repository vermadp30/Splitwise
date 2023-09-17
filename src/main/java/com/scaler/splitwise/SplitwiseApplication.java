package com.scaler.splitwise;

import com.scaler.splitwise.commands.CommandRegistry;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {

  private Scanner scanner;
  private CommandRegistry commandRegistry;

  @Autowired
  public SplitwiseApplication(CommandRegistry commandRegistry) {
    this.scanner = new Scanner(System.in);
    this.commandRegistry = commandRegistry;
  }

  public static void main(String[] args) {
    SpringApplication.run(SplitwiseApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    while (true) {
      System.out.println("Please provide input for Splitwise Application: ");
      String input = scanner.nextLine();
      commandRegistry.execute(input);
    }
  }
}
