package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupRequestDto {
  private String inputUserId;
  private String groupName;
}
