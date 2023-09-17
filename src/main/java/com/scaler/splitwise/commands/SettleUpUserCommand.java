package com.scaler.splitwise.commands;

import com.scaler.splitwise.controllers.UserExpenseController;
import com.scaler.splitwise.dtos.SettleUpUserRequestDto;
import com.scaler.splitwise.dtos.SettleUpUserResponseDto;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettleUpUserCommand implements Command {

  UserExpenseController controller;

  @Autowired
  public SettleUpUserCommand(UserExpenseController controller) {
    this.controller = controller;
  }

  @Override
  public boolean matches(String input) {
    List<String> inputList = Arrays.stream(input.split(" ")).toList();
    return inputList.size() == 2 && inputList.get(1).equalsIgnoreCase(CommandKeywords.SETTLE_UP);
  }

  @Override
  public void execute(String input) {
    List<String> inputList = Arrays.stream(input.split(" ")).toList();
    String inputUserId = inputList.get(0);
    SettleUpUserRequestDto request = new SettleUpUserRequestDto();
    request.setUserId(inputUserId);

    SettleUpUserResponseDto response = new SettleUpUserResponseDto();
    response = controller.settleUp(request);

    System.out.println(response.toString());
  }
}
