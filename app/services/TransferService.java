package services;
import dao.TransferMoneyDao;
import daolayer.DbConnector;
import dto.LoginRequest;
import dto.TransferMoneyRequest;
import models.*;
import javax.inject.Singleton;
import java.util.Date;

@Singleton
public class TransferService {

    public static void transferSaveData(TransferMoneyRequest transferRequestDto){

        Bank bankDetails = new Bank();
        bankDetails.setIfsc(transferRequestDto.getIfsc());
        bankDetails.setAccountNo(transferRequestDto.getAccountNumber());
        bankDetails.setAmountToTransact(transferRequestDto.getAmount());
        bankDetails.setDescription(transferRequestDto.getDescription());


        TransactionType transactionType=TransactionType.find.byId(1);


        bankDetails.setTransactionTypeId(transactionType);

        //LoginRequest loginRequest=new LoginRequest();
        User user= User.find.byId(13);
        bankDetails.setUserId(user);
        TransferMoneyDao.saveTransferData(bankDetails);

        Transaction transactionHistory = new Transaction();
        transactionHistory.setUserId(user);
        transactionHistory.setAmount(transferRequestDto.getAmount());

        TransactionType transactionTypeId=TransactionType.find.byId(1);
        transactionHistory.setTransactionTypeId(transactionTypeId);
        transactionHistory.setCreatedOn(new Date());
        transactionHistory.setDescription(transferRequestDto.getDescription());

        TransactionAction transactionActionId= TransactionAction.find.byId(1);
        transactionHistory.setActionTypeId(transactionActionId);

        transactionHistory.setBankDetailsId(bankDetails);

        DbConnector.save(transactionHistory);




    }


}
