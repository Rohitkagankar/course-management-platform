package com.project.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AopClass {
	
	@Pointcut("execution(* com.project.service.*.*(..))")
	public void k() {};
	
	@Before("execution(* com.project.service.InstructorServiceImpl.add(..))")
	public void beforeAddInstructor(JoinPoint jp) {
		System.out.println("Before addding instructor...");
	}
	
	@After("execution(* com.project.service.InstructorServiceImpl.createInstructor(..))")
	public void afterAddInstructor(JoinPoint jp) {
		System.out.println("after addding instructor...");
	}
	
	@Around("k()")
	public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("befor Around method called");
		Object result=pjp.proceed();
		System.out.println("after Around method called");
		return result;
	}
	
	
	
//	@AfterThrowing(pointcut = "execution(* com.*(..))", throwing = "error")
//	public void afterThrowingMethod(JoinPoint jp, Throwable error)// it is advice
//	{
//		System.out.println("Start of after throwing advice..");
//		System.out.println("Method Signature: " + jp.getSignature());
//		System.out.println("Exception is: " + error);
//		System.out.println("end of after throwing advice...");
//	}
}
