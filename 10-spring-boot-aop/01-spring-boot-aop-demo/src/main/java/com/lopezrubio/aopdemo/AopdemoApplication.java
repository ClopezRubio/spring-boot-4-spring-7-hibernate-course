package com.lopezrubio.aopdemo;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.lopezrubio.aopdemo.dao.AccountDAO;
import com.lopezrubio.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
        return runner -> {
            demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO,  MembershipDAO theMembershipDAO) {

        // call the business method
        Account theAccount = new Account();
        boolean vipFlag = true;
        theAccountDAO.addAccount(theAccount, vipFlag);
        System.out.println();

        theAccountDAO.doWork();

        // Call the membership business method
        theMembershipDAO.addMembershipAccount();
        theMembershipDAO.goToSleep();

    }

}
