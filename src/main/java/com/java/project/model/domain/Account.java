package com.java.project.model.domain;

import java.math.BigDecimal;
import java.util.*;

/**
 * Represents abstract entity Account
 */
public abstract class Account {

    /**
     * Account id.
     */
    private int id;

    /**
     * Account number.
     */
    private long number;

    /**
     * Account balance.
     */
    private BigDecimal balance;

    /**
     * Account owner's id.
     */
    private int userId;

    /**
     * Account expiration date.
     */
    private Date expirationDate;

    /**
     * Account status(Opened, Closed or Under consideration.
     */
    private AccountStatus status;

    /**
     * Constructor which is used in  GenericDAOImpl.
     */
    public Account() {

    }

    /**
     * Constructor which is used for creating new account.
     */
    public Account(long number, int userId, BigDecimal balance) {
        this.number = number;
        this.userId = userId;
        this.balance = balance;
    }

    /**
     * Constructor which is used for reading existing account from database.
     */
    public Account(int id, long number, int userId, BigDecimal balance, AccountStatus status, Date expirationDate) {
        this.id = id;
        this.number = number;
        this.userId = userId;
        this.balance = balance;
        this.status = status;
        this.expirationDate = expirationDate;
    }

    /**
     * Calculates account expiration date.
     */
    public abstract void calculateExpirationDate();


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

}
