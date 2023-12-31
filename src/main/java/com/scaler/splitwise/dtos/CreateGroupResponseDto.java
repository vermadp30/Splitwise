package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupResponseDto {
  private Long groupId;
  private String status;
  private String message;

  @Override
  public String toString() {
    return "Create Group Response {"
        + "groupId="
        + groupId
        + ", status='"
        + status
        + '\''
        + ", message='"
        + message
        + '\''
        + '}';
  }
}
