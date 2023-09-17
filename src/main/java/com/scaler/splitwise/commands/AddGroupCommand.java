package com.scaler.splitwise.commands;

import com.scaler.splitwise.controllers.GroupController;
import com.scaler.splitwise.dtos.CreateGroupRequestDto;
import com.scaler.splitwise.dtos.CreateGroupResponseDto;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddGroupCommand implements Command {

  private GroupController groupController;

  @Autowired
  public AddGroupCommand(GroupController groupController) {
    this.groupController = groupController;
  }

  @Override
  public boolean matches(String input) {
    List<String> inputList = Arrays.stream(input.split(" ")).toList();
    return inputList.size() == 3 && inputList.get(1).equalsIgnoreCase(CommandKeywords.ADD_GROUP);
  }

  @Override
  public void execute(String input) {
    List<String> inputList = Arrays.stream(input.split(" ")).toList();
    String inputUserId = inputList.get(0);
    String groupName = inputList.get(2);

    CreateGroupRequestDto request = new CreateGroupRequestDto();
    request.setInputUserId(inputUserId);
    request.setGroupName(groupName);

    CreateGroupResponseDto response = new CreateGroupResponseDto();
    response = groupController.createGroup(request);

    System.out.println(response);
  }
}
