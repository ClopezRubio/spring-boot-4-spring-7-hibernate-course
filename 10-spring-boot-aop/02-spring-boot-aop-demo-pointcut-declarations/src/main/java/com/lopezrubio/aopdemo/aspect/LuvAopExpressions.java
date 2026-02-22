package com.lopezrubio.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {

    // utility class used for sharing Pointcut declarations

    @Pointcut("execution(* com.lopezrubio.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){}

    // create a pointcut for getter methods
    @Pointcut("execution(* com.lopezrubio.aopdemo.dao.*.get*(..))")
    public void getter() {}

    // create a pointcut for setter methods
    @Pointcut("execution(* com.lopezrubio.aopdemo.dao.*.set*(..))")
    public void setter() {}

    // create pointcut: include package and exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter(){}

}
