package com.lopezrubio.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public boolean addMembershipAccount() {
        System.out.println(getClass() + ": ADDING A MEMBERSHIP ACCOUNT");

        return false;
    }

    @Override
    public void goToSleep() {

        System.out.println(getClass() + ": GOING TO SLEEP");

    }

}
