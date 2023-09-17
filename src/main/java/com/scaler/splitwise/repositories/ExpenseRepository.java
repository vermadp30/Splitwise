package com.scaler.splitwise.repositories;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.Group;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

  List<Expense> findAllByGroup(Group group);
}
