package com.scaler.splitwise.models;

import com.scaler.splitwise.enums.UserExpenseType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserExpense extends BaseModel {

  @ManyToOne private User user;

  @ManyToOne private Expense expense;

  @Enumerated(value = EnumType.ORDINAL)
  private UserExpenseType UserExpenseType;

  private int amount;
}
