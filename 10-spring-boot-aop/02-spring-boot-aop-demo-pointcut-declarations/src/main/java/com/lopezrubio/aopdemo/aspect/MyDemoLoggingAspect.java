package com.lopezrubio.aopdemo.aspect;

import com.lopezrubio.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    @AfterThrowing(
            pointcut="execution(* com.lopezrubio.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable theExc) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>> Executing @AfterThrowing advice on method: " + method);

        // log the exception
        System.out.println("\n=====>> The exception is: " + theExc);

    }

    @Before("com.lopezrubio.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccount(JoinPoint theJoinPoint) {
        System.out.println("\n =======> Executing @Before advice on method <=======");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        // display the method arguments

        // get args
        Object[] args = theJoinPoint.getArgs();

        // loop the args
        for (Object arg : args) {
            System.out.println(arg);

            if(arg instanceof Account){
                // downcast and print Account info
                Account theAccount = (Account) arg;
                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account level: " + theAccount.getLevel());
            }
        }

    }

    // add a new advice for @AfterReturning on the findAccounts method

    @AfterReturning(
            pointcut = "execution(* com.lopezrubio.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>> Executing @AfterReturning advice on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>> The result is: " + result);

        // post-process the data...modify it
        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>> The modified result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts
        for(Account theAccount : result){
            // get uppercase version of the name & update the name
            theAccount.setName(theAccount.getName().toUpperCase());
        }
    }

    @After("execution(* com.lopezrubio.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>> Executing @After (finally) advice on method: " + method);
    }

    @Around("execution(* com.lopezrubio.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint  theProceedingJoinPoint) throws Throwable{

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>> Executing @Around advice on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // execute the method
        Object result = null;

        try{
            result = theProceedingJoinPoint.proceed();
        }catch(Exception ex){
            // log the exception
            System.out.println(ex.getMessage());

            /* // give user a custom message
            result = "Major accident! But no worries, your private AOP helicopter is here to pick you up!";
             */

            throw ex;
        }

        // get the end timestamp
        long end = System.currentTimeMillis();

        // compute the duration and display it
        long duration =  end - begin;
        System.out.println("\n=====>>> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

}
