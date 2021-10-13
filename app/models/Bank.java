package models;

import io.ebean.Finder;

import javax.persistence.*;

@Entity(name = "bank")
public class Bank {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "ifsc")
    private int ifsc;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "amount_to_transact")
    private String amountToTransact;

    @ManyToOne
    @JoinColumn(name = "tt_id", referencedColumnName = "id")
    private TransactionType transactionTypeId;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIfsc() {
        return ifsc;
    }

    public void setIfsc(int ifsc) {
        this.ifsc = ifsc;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAmountToTransact() {
        return amountToTransact;
    }

    public void setAmountToTransact(String amountToTransact) {
        this.amountToTransact = amountToTransact;
    }

    public TransactionType getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(TransactionType transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public static final Finder<Integer, Bank> find = new Finder<>(Bank.class);


}
