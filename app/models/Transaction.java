package models;

import io.ebean.Finder;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User userId;

    @Column(name = "amount")
    private String amount;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id",referencedColumnName = "id")
    private TransactionType transactionTypeId;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "action_type_id",referencedColumnName = "id")
    private TransactionAction actionTypeId;

    @ManyToOne
    @JoinColumn(name = "bank_details_id",referencedColumnName = "id")
    private Bank bankDetailsId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(TransactionType transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionAction getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(TransactionAction actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    public Bank getBankDetailsId() {
        return bankDetailsId;
    }

    public void setBankDetailsId(Bank bankDetailsId) {
        this.bankDetailsId = bankDetailsId;
    }

    public static final Finder<Integer, Transaction> find = new Finder<>(Transaction.class);
}
