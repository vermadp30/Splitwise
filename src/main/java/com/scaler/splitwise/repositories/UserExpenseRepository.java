package com.scaler.splitwise.repositories;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.models.UserExpense;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {

  List<UserExpense> findAllByUser(User user);

  List<UserExpense> findAllByExpense(List<Expense> expenses);
}
