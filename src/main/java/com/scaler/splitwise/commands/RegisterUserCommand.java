package com.scaler.splitwise.commands;

import com.scaler.splitwise.controllers.UserController;
import com.scaler.splitwise.dtos.CreateUserRequestDto;
import com.scaler.splitwise.dtos.CreateUserResponseDto;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

// Register vinsmokesanji 003 namisswwaann
@Component
public class RegisterUserCommand implements Command {

  private UserController userController;

  public RegisterUserCommand(UserController userController) {
    this.userController = userController;
  }

  @Override
  public boolean matches(String input) {
    List<String> inputWords = Arrays.stream(input.split(" ")).toList();
    return inputWords.size() == 4
        && inputWords.get(0).equalsIgnoreCase(CommandKeywords.REGISTER_USER);
  }

  @Override
  public void execute(String input) {
    List<String> inputWords = Arrays.stream(input.split(" ")).toList();
    String username = inputWords.get(1);
    String mobile = inputWords.get(2);
    String password = inputWords.get(3);

    CreateUserRequestDto request = new CreateUserRequestDto();
    request.setUserName(username);
    request.setMobile(mobile);
    request.setPassword(password);

    CreateUserResponseDto response = new CreateUserResponseDto();
    response = userController.registerUser(request);
    System.out.println(response.toString());
  }
}
