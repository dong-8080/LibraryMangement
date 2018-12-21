package com.example.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class UserEntity {

    private String account;
    private String password;
    private String name;
    private int limit;
    private int amount;

    public UserEntity() {
    }

    public UserEntity(String account, String password, String name, int limit, int amount) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.limit = limit;
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", limit=" + limit +
                ", amount=" + amount +
                '}';
    }
}
