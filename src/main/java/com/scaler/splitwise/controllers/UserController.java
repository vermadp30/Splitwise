package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.CreateUserRequestDto;
import com.scaler.splitwise.dtos.CreateUserResponseDto;
import com.scaler.splitwise.dtos.UpdatePasswordRequestDto;
import com.scaler.splitwise.dtos.UpdatePasswordResponseDto;
import com.scaler.splitwise.exceptions.UserAlreadyExistsException;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  public CreateUserResponseDto registerUser(CreateUserRequestDto request) {

    String userName = request.getUserName();
    String mobile = request.getMobile();
    String password = request.getPassword();

    CreateUserResponseDto response = new CreateUserResponseDto();

    User user;

    try {
      user = userService.registerUser(userName, mobile, password);
    } catch (UserAlreadyExistsException exception) {
      System.out.println(exception.getMessage());
      response.setStatus("FAILED");
      response.setStatus("User with mobile " + mobile + " already present");
      return response;
    }
    response.setUserId(user.getId());
    response.setStatus("SUCCESS");
    response.setMessage("User created successfully");

    return response;
  }

  public UpdatePasswordResponseDto updateUserPassword(UpdatePasswordRequestDto request) {

    String userId = request.getUserId();
    String password = request.getPassword();

    UpdatePasswordResponseDto response = new UpdatePasswordResponseDto();

    User user;

    try {
      user = userService.updateUserPassword(userId, password);
    } catch (Exception exception) {
      response.setStatus("FAILED");
      response.setMessage(exception.getMessage());
      return response;
    }
    response.setUserId(user.getId());
    response.setStatus("SUCCESS");
    response.setMessage("Password updated successfully");
    return response;
  }
}
