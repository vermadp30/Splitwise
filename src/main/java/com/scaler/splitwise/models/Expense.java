package com.scaler.splitwise.models;

import com.scaler.splitwise.enums.ExpenseType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Expense extends BaseModel {

  private int amount;

  private String description;

  @Enumerated(value = EnumType.ORDINAL)
  private ExpenseType expenseType;

  @ManyToOne private User createdBy;

  @ManyToOne private Group groups;
}
