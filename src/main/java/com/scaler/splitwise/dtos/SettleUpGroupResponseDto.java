package com.scaler.splitwise.dtos;

import com.scaler.splitwise.services.Transaction;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleUpGroupResponseDto {
  private List<Transaction> transactions;
  private String status;
  private String message;

  @Override
  public String toString() {
    return "Settle-Up Group{"
        + "transactions="
        + transactions
        + ", status='"
        + status
        + '\''
        + ", message='"
        + message
        + '\''
        + '}';
  }
}
