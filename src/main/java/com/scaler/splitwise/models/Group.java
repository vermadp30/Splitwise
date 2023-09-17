package com.scaler.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "groups")
public class Group extends BaseModel {

  private String name;

  private String description;

  @ManyToMany private List<User> members;

  @ManyToOne private User createdBy;
}
