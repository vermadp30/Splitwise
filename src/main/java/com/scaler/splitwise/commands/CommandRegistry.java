package com.scaler.splitwise.commands;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandRegistry {
  private List<Command> commands;

  @Autowired
  public CommandRegistry(RegisterUserCommand registerUserCommand) {
    commands = new ArrayList<>();
    commands.add(registerUserCommand);
  }

  public void execute(String input) {
    for (Command command : commands) {
      if (command.matches(input)) {
        command.execute(input);
        break;
      }
    }
  }
}
