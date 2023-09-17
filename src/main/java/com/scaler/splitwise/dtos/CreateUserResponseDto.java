package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponseDto {
  private Long userId;
  private String status;
  private String message;

  @Override
  public String toString() {
    return "Create User Response{"
        + "userId="
        + userId
        + ", status='"
        + status
        + '\''
        + ", message='"
        + message
        + '\''
        + '}';
  }
}
