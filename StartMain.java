
public class StartMain {
	public static void main(String[] args) {
		CrudInterface alg = new CrudAlgoritm();
		OperationInterface oper = new Operation(alg);
		ControlConsole control = new ControlConsole(alg,oper);
		control.outputMenu();
	}
}
