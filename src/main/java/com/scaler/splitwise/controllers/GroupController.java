package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.CreateGroupRequestDto;
import com.scaler.splitwise.dtos.CreateGroupResponseDto;
import com.scaler.splitwise.models.Group;
import com.scaler.splitwise.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {

  private GroupService groupService;

  @Autowired
  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  public CreateGroupResponseDto createGroup(CreateGroupRequestDto request) {
    CreateGroupResponseDto response = new CreateGroupResponseDto();
    String inputUserId = request.getInputUserId();
    String groupName = request.getGroupName();
    Group group;

    try {
      group = groupService.createGroup(inputUserId, groupName);
    } catch (Exception exception) {
      response.setStatus("FAILED");
      response.setMessage(exception.getMessage());
      return response;
    }
    response.setStatus("SUCCESS");
    response.setMessage("Group created with title: " + groupName);
    response.setGroupId(group.getId());
    return response;
  }
}
