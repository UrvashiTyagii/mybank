package dao;

import daolayer.DbConnector;
import models.Bank;

public class TransferMoneyDao {

    public static void saveTransferData(Bank bankDetails)
    {
        DbConnector.save(bankDetails);

    }
}
