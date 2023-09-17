package com.scaler.splitwise.strategies;

import com.scaler.splitwise.enums.UserExpenseType;
import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.models.UserExpense;
import com.scaler.splitwise.repositories.UserExpenseRepository;
import com.scaler.splitwise.services.Transaction;
import java.util.*;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("twoSetsSettleUp")
public class TwoSetsSettleUpStrategy implements SettleUpStrategy {

  private UserExpenseRepository userExpenseRepository;

  @Autowired
  public TwoSetsSettleUpStrategy(UserExpenseRepository userExpenseRepository) {
    this.userExpenseRepository = userExpenseRepository;
  }

  @Override
  public List<Transaction> settle(List<Expense> expenses) {

    List<UserExpense> allUserExpensesForTheseExpenses =
        userExpenseRepository.findAllByExpense(expenses);

    HashMap<User, Integer> moneyPaidExtra = new HashMap<>();

    for (UserExpense userExpense : allUserExpensesForTheseExpenses) {
      User user = userExpense.getUser();
      int currentExtraPaid = 0;

      if (moneyPaidExtra.containsKey(user)) {
        currentExtraPaid = moneyPaidExtra.get(user);
      }

      if (userExpense.getUserExpenseType().equals(UserExpenseType.PAID)) {
        moneyPaidExtra.put(user, currentExtraPaid + userExpense.getAmount());
      } else {
        moneyPaidExtra.put(user, currentExtraPaid - userExpense.getAmount());
      }
    }

    TreeSet<Pair<User, Integer>> extraPaid = new TreeSet<>();
    TreeSet<Pair<User, Integer>> lessPaid = new TreeSet<>();

    for (Map.Entry<User, Integer> userAmount : moneyPaidExtra.entrySet()) {
      if (userAmount.getValue() < 0) {
        lessPaid.add(new Pair<>(userAmount.getKey(), userAmount.getValue()));
      } else {
        extraPaid.add(new Pair<>(userAmount.getKey(), userAmount.getValue()));
      }
    }

    List<Transaction> transactions = new ArrayList<>();

    while (!lessPaid.isEmpty()) {
      Pair<User, Integer> lessPaidPair = lessPaid.pollFirst();
      Pair<User, Integer> extraPaidPair = extraPaid.pollFirst();

      Transaction t = new Transaction();

      t.setFrom(lessPaidPair.a);
      t.setTo(extraPaidPair.a);

      if (Math.abs(lessPaidPair.b) < Math.abs(extraPaidPair.b)) {
        t.setAmount(Math.abs((lessPaidPair.b)));
        extraPaid.add(new Pair<>(extraPaidPair.a, extraPaidPair.b - Math.abs(lessPaidPair.b)));
      } else if (Math.abs(lessPaidPair.b) > Math.abs(extraPaidPair.b)) {
        t.setAmount(extraPaidPair.b);
        lessPaid.add(new Pair<>(lessPaidPair.a, lessPaidPair.b + extraPaidPair.b));
      } else {
        t.setAmount(extraPaidPair.b);
      }
    }
    return transactions;
  }
}
