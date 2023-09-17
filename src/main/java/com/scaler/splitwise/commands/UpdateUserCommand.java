package com.scaler.splitwise.commands;

import com.scaler.splitwise.controllers.UserController;
import com.scaler.splitwise.dtos.UpdatePasswordRequestDto;
import com.scaler.splitwise.dtos.UpdatePasswordResponseDto;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommand implements Command {

  private UserController userController;

  public UpdateUserCommand(UserController userController) {
    this.userController = userController;
  }

  @Override
  public boolean matches(String input) {
    List<String> inputList = Arrays.stream(input.split(" ")).toList();
    return inputList.size() == 3
        && inputList.get(1).equalsIgnoreCase(CommandKeywords.UPDATE_PROFILE);
  }

  @Override
  public void execute(String input) {
    List<String> inputList = Arrays.stream(input.split(" ")).toList();
    String userId = inputList.get(0);
    String password = inputList.get(1);

    UpdatePasswordRequestDto request = new UpdatePasswordRequestDto();
    request.setUserId(userId);
    request.setPassword(password);

    UpdatePasswordResponseDto response = new UpdatePasswordResponseDto();
    response = userController.updateUserPassword(request);
    System.out.println(response.toString());
  }
}
