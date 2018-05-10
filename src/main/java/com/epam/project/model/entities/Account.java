package com.epam.project.model.entities;

import java.math.BigDecimal;
import java.util.*;

public abstract class Account {

    private int id;
    private BigDecimal balance;
    private int userId;
    private List<Operation> history;
    private Date expirationDate;
    private AccountStatus status;

    public Account() {
        expirationDate = calculateExpirationDate();
    }

    public Account(int userId, BigDecimal balance) {
        this.userId = userId;
        this.balance = balance;
        this.status = AccountStatus.UNDER_CONSIDERATION;
        history = new ArrayList<>();
        expirationDate = calculateExpirationDate();
        System.out.println(expirationDate);
    }


    private Date calculateExpirationDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(Calendar.YEAR, 2);
        return gregorianCalendar.getTime();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getUserId() {
        return userId;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
