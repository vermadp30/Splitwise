package com.scaler.splitwise.strategies;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.services.Transaction;
import java.util.List;

public interface SettleUpStrategy {
  List<Transaction> settle(List<Expense> expenses);
}
