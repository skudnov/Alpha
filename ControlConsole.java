import java.util.Scanner;
import java.util.Map;
public class ControlConsole{
	private static Scanner sc = new Scanner(System.in);
	private String key,value,command;
	private int enterNumber;
	private OperationInterface oper;
	ControlConsole(OperationInterface oper){
		this.oper = oper;
	}
	
	
	
	public void outputMenu(){
		System.out.println(oper.openFile());
		
		
		while(oper.checkAplhaCount()) {
			  System.out.println("\nMenu: \n1: Print all values \n2: Key search \n3: Key delete \n4: Key add");
			 
				  command = sc.nextLine();
		    switch(command) {
		      case "1": 
					System.out.println("Все значения из словаря(ей)");
				  
					for (Map.Entry entry : oper.printAlpha().entrySet()) {
						System.out.println("Key: " + entry.getKey() + " Value: "
						+ entry.getValue());
					}
				  break;
			
			 case "2": 
		    	  System.out.println("Введите ключ");
		    	  key = sc.next();
		    	  System.out.println(oper.getValue(key));
		    	  break;
			case "3":
		    	  System.out.println("Введите ключ:");
		    	  key = sc.next();
		    	  System.out.println("Выберите словарь");
				  int count =0;
				  for(Object nameFile : oper.getAlphaName()){
					  System.out.println(count+": "+ nameFile);
					  count++;
				  }	
						while (true) {					  
					enterNumber = oper.scannerInt(sc);
					  if(enterNumber>=0 && enterNumber<count)
						  break;
					  else
						  System.out.println("Ошибка,выберите номерь словаря");
				  }
		    	  System.out.println(oper.removeKey(key,enterNumber));
		    	  break;
		    	 
			case "4":
				System.out.println("Выберите словарь");
				  count =0;
				  for(Object nameFile : oper.getAlphaName()){
					  System.out.println(count+": "+ nameFile);
					  count++;
				  }
				  while (true) {
					  enterNumber = oper.scannerInt(sc);
					  if(enterNumber>=0 && enterNumber<count)
						  break;
					 else
						  System.out.println("Ошибка,заново выберите словарь");
					
					
					break;
				  }
				  
				  while (true) {
				  System.out.println("Введите ключ:");
		    	  key = sc.next();
		    	  System.out.println("Введите значение:");
				  value = sc.next();
				 
					  System.out.println(oper.addKey(key, value,enterNumber));
					break;
				  }
				  break;
			default: System.out.println("Неверная команда");
			}
             sc.nextLine();
		  }
	}
	
}