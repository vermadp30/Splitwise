package com.scaler.splitwise.services;

import com.scaler.splitwise.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Transaction {
  private User to;
  private User from;
  private int amount;

  @Override
  public String toString() {
    return "Transaction{" + "to=" + to + ", from=" + from + ", amount=" + amount + '}';
  }
}
