package com.scaler.splitwise.models;

import com.scaler.splitwise.enums.UserStatus;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class User extends BaseModel {

  private String userName;

  private String mobile;

  private String password;

  @ManyToMany(mappedBy = "members")
  private List<Group> groups;

  @Enumerated(value = EnumType.ORDINAL)
  private UserStatus userStatus;
}
