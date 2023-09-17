package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordResponseDto {
  private Long userId;
  private String status;
  private String message;

  @Override
  public String toString() {
    return "Update Profile Response{"
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
