package services;

import daolayer.DbConnector;
import dto.LoginRequest;
import dto.TransactionHistoryRequest;
import dto.TransactionHistoryResponse;
import dto.TransferMoneyRequest;
import models.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionService {
    public List<TransactionHistoryResponse> transactionHistory(TransactionHistoryRequest transactionHistoryRequestDto)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        List<Transaction> transactionList = Transaction.find.query().where().eq("userId.id",transactionHistoryRequestDto.getUserId()).findList();
        List<TransactionHistoryResponse> transactionHistoryResponseList= new ArrayList<TransactionHistoryResponse>();
        for (Transaction transaction: transactionList)
        {
            TransactionHistoryResponse transactionHistoryResponseDto = new TransactionHistoryResponse();
            transactionHistoryResponseDto.setId(transaction.getId());
            transactionHistoryResponseDto.setAmount(transaction.getAmount());
            transactionHistoryResponseDto.setDate(sdf.format(transaction.getCreatedOn()));
            transactionHistoryResponseDto.setDescription(transaction.getDescription());
            transactionHistoryResponseList.add(transactionHistoryResponseDto);

        }
        return transactionHistoryResponseList;

    }
}
