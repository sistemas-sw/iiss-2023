package com.iiss;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BankAccountApplication {

	public static void main(String[] args) {
		
		int respuesta;

		ApplicationContext rootctx
            = new ClassPathXmlApplicationContext(
                "springContext.xml");

		//ID = 1111  Date = now
		BankAccount obj1
            = (BankAccount)rootctx
                  .getBean("InjectwithConstructor");

		//ID = 2222  Date = 17/10/2001
		BankAccount obj2
            = (BankAccount)rootctx
                  .getBean("InjectwithConstructor2");
		
		//ID = 3333  Date = now
		BankAccount obj3
            = (BankAccount)rootctx
                  .getBean("InjectwithSetter");

		System.out.println("\nInyecciones ^^^ ===================  Comparaciones >>>\n");

		respuesta = obj1.compareTo(obj1);
		System.out.println("Comparacion a un mismo objeto mediante ID: " + respuesta);
		respuesta = obj1.compareTo(obj3);
		System.out.println("Comparacion mediante ID: " + respuesta);
		
		respuesta = obj3.compareTo(obj1);
		System.out.println("Comparacion mediante Fecha (iguales): " + respuesta);
		respuesta = obj3.compareTo(obj2);
		System.out.println("Comparacion mediante Fecha (distintas): " + respuesta);
	}

}
