package lib;
import lib.config.DBContext;
import lib.config.FileContext;
import lib.view.ControlConsole;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartMain {
	public static void main(String[] args) {


		ApplicationContext ctx;
		switch (args[0]) {
			case "file":
				ctx = new AnnotationConfigApplicationContext(FileContext.class);
				break;
			case "database":
				ctx = new AnnotationConfigApplicationContext(DBContext.class);
				break;
			default:
				System.out.println("Invalid input parameter!");
				return;
		}
		ControlConsole control = ctx.getBean(ControlConsole.class);
		control.outputMenu();

	}

}