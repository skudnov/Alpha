package com.lib;
import com.lib.*;
public class StartMain {
	public static void main(String[] args) {
		ICrud alg = new CrudAlgoritm();
		IOperation oper = new Operation(alg);
		ControlConsole control = new ControlConsole(oper);
		control.outputMenu();
	}
}