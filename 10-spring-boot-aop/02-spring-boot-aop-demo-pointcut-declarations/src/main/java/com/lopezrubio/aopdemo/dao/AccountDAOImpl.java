package com.lopezrubio.aopdemo.dao;

import com.lopezrubio.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    @Override
    public void addAccount(Account account,  boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {

        System.out.println(getClass() + ": doWork()");

        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {

        return findAccounts(false);

    }

    @Override
    public List<Account> findAccounts(boolean tripWire){

        // for academic purposes... simulate an exception
        if(tripWire){
            throw new RuntimeException("No soup for you!!!");
        }

        List<Account> myAccounts = new ArrayList<>();

        // create sample accounts

        Account myAccount1 = new Account("John", "Silver");
        Account myAccount2 = new Account("Madhou", "Platinum");
        Account myAccount3 = new Account("Luka", "Gold");

        // add them to our accounts list
        myAccounts.add(myAccount1);
        myAccounts.add(myAccount2);
        myAccounts.add(myAccount3);

        return myAccounts;

    }

}
