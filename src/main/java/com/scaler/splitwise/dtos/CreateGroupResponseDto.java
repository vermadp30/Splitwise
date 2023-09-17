package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupResponseDto {
  private Long groupId;
  private String status;
  private String message;
}
