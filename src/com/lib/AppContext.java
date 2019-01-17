package com.lib;
import org.springframework.context.annotation.*;
@Configuration
public class AppContext {
    @Bean
    public ICrud alg() {
        ICrud alg = new CrudAlgoritm();
        return alg;
    }
 
    @Bean
    public IOperation  oper() {
       IOperation oper = new Operation();
        return oper;
    }
 
    @Bean
    public ICheckInputAlpha  input() {
		ICheckInputAlpha input = new CheckInputAlpha();
        return input;
    }
	
	 @Bean
    public ControlConsole control() {
		ControlConsole control = new ControlConsole();
        return control;
    }
}