package com.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;



@Aspect
public class LameLogger {
	
//	@Before("execution(* com.dao.*(..))")
//	public void logBefore(JoinPoint jP){
//		System.out.println("==========================");
//		System.out.println("Executing a log before a dao ");
//		System.out.println("at :"+jP.getSignature().getName());
//		System.out.println("\n");
//	}

}
