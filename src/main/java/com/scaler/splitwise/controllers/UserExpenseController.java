package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.SettleUpGroupRequestDto;
import com.scaler.splitwise.dtos.SettleUpGroupResponseDto;
import com.scaler.splitwise.dtos.SettleUpUserRequestDto;
import com.scaler.splitwise.dtos.SettleUpUserResponseDto;
import com.scaler.splitwise.services.ExpenseService;
import com.scaler.splitwise.services.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserExpenseController {

  ExpenseService expenseService;

  @Autowired
  public UserExpenseController(ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  public SettleUpUserResponseDto settleUp(SettleUpUserRequestDto request) {

    String inputUserId = request.getUserId();
    SettleUpUserResponseDto response = new SettleUpUserResponseDto();

    List<Transaction> transactions;

    try {
      transactions = expenseService.settleUpUser(inputUserId);
    } catch (Exception e) {
      response.setMessage(e.getMessage());
      response.setStatus("FAILED");
      return response;
    }
    response.setStatus("SUCCESS");
    response.setMessage("Transactions Settled Up");
    response.setTransactions(transactions);

    return response;
  }

  public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto request) {

    String inputUserId = request.getUserId();
    String inputGroupId = request.getGroupId();

    SettleUpGroupResponseDto response = new SettleUpGroupResponseDto();

    List<Transaction> transactions;

    try {
      transactions = expenseService.settleUpGroup(inputUserId, inputGroupId);
    } catch (Exception e) {
      response.setMessage(e.getMessage());
      response.setStatus("FAILED");
      return response;
    }
    response.setStatus("SUCCESS");
    response.setMessage("Transactions Settled Up");
    response.setTransactions(transactions);

    return response;
  }
}
