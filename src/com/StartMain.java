package com.lib;
import com.lib.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;

public class StartMain {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		ControlConsole control = ctx.getBean(ControlConsole.class);
		control.outputMenu();
		
	}
}