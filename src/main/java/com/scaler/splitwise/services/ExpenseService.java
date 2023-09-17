package com.scaler.splitwise.services;

import com.scaler.splitwise.exceptions.GroupNotFoundException;
import com.scaler.splitwise.exceptions.InvalidArgumentException;
import com.scaler.splitwise.exceptions.UserNotFoundException;
import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.Group;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.models.UserExpense;
import com.scaler.splitwise.repositories.ExpenseRepository;
import com.scaler.splitwise.repositories.GroupRepository;
import com.scaler.splitwise.repositories.UserExpenseRepository;
import com.scaler.splitwise.repositories.UserRepository;
import com.scaler.splitwise.strategies.SettleUpStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

  private UserRepository userRepository;
  private GroupRepository groupRepository;
  private ExpenseRepository expenseRepository;
  private UserExpenseRepository userExpenseRepository;
  private SettleUpStrategy settleUpStrategy;

  @Autowired
  public ExpenseService(
      UserRepository userRepository,
      GroupRepository groupRepository,
      ExpenseRepository expenseRepository,
      UserExpenseRepository userExpenseRepository,
      @Qualifier("twoSetsSettleUp") SettleUpStrategy settleUpStrategy) {
    this.userRepository = userRepository;
    this.groupRepository = groupRepository;
    this.expenseRepository = expenseRepository;
    this.userExpenseRepository = userExpenseRepository;
    this.settleUpStrategy = settleUpStrategy;
  }

  public List<Transaction> settleUpUser(String inputUserId)
      throws InvalidArgumentException, UserNotFoundException {

    Long userId = 0L;

    try {
      userId = Long.parseLong(inputUserId);
    } catch (Exception exception) {
      throw new InvalidArgumentException("Invalid userId provided");
    }

    Optional<User> userOptional = userRepository.findById(userId);

    if (userOptional.isEmpty()) {
      throw new UserNotFoundException("User with User Id: " + userId + " does not exist.");
    }

    User user = userOptional.get();

    List<UserExpense> userExpenses = userExpenseRepository.findAllByUser(user);

    List<Expense> expensesInvolvingUser = new ArrayList<>();
    for (UserExpense userExpense : userExpenses) {
      expensesInvolvingUser.add(userExpense.getExpense());
    }

    List<Transaction> transactions = settleUpStrategy.settle(expensesInvolvingUser);

    return transactions;
  }

  public List<Transaction> settleUpGroup(String inputUserId, String inputGroupId)
      throws InvalidArgumentException, UserNotFoundException, GroupNotFoundException {

    Long userId = 0L;
    Long groupId = 0L;

    try {
      userId = Long.parseLong(inputUserId);
    } catch (Exception exception) {
      throw new InvalidArgumentException("Invalid userId provided");
    }

    Optional<User> userOptional = userRepository.findById(userId);

    if (userOptional.isEmpty()) {
      throw new UserNotFoundException("User with User Id: " + userId + " does not exist.");
    }

    try {
      groupId = Long.parseLong(inputGroupId);
    } catch (Exception exception) {
      throw new InvalidArgumentException("Invalid userId provided");
    }

    Optional<Group> groupOptional = groupRepository.findById(groupId);

    if (groupOptional.isEmpty()) {
      throw new GroupNotFoundException("Group with Group Id: " + groupId + " does not exist.");
    }

    Group group = groupOptional.get();

    List<Expense> expenses = expenseRepository.findAllByGroup(group);

    List<Transaction> transactions = settleUpStrategy.settle(expenses);

    return transactions;
  }
}
