package com.lopezrubio.aopdemo.dao;

import com.lopezrubio.aopdemo.Account;

public interface AccountDAO {

    void addAccount(Account account, boolean vipFlag);

    boolean doWork();
}
