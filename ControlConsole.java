import java.util.Scanner;
import java.util.Map;
public class ControlConsole{
	private static Scanner sc = new Scanner(System.in);
	private String key,value;
	private int enterNumber;
	private CrudInterface alg;
	private OperationInterface oper;
	ControlConsole(CrudInterface alg, OperationInterface oper){
		this.alg = alg;
		this.oper = oper;
	}
	
	
	
	public void outputMenu(){
		System.out.println(alg.openFile());
		
		
		while(oper.checkAplhaCount()) {
			  System.out.println("\nMenu: \n1: Print all values \n2: Key search \n3: Key delete \n4: Key add");
			 while (true) {					  
					enterNumber = oper.scannerInt(sc);
					  if(enterNumber>=0 && enterNumber<=4)
						  break;
					  else
						  System.out.println("Ошибка,заново выберите пункт меню!");
				  }
				  
		    switch(enterNumber) {
		      case 1: 
					System.out.println("Все значения из словаря(ей)");
				  
					for (Map.Entry entry : oper.printAlpha().entrySet()) {
						System.out.println("Key: " + entry.getKey() + " Value: "
						+ entry.getValue());
					}
				  break;
			
			 case 2: 
		    	  System.out.println("Введите ключ");
		    	  key = sc.next();
		    	  System.out.println(oper.getValue(key));
		    	  break;
			case 3:
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
		    	  System.out.println(alg.removeKey(key,enterNumber));
		    	  break;
			case 4:
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
				  }
				  
				  while (true) {
				  System.out.println("Введите ключ:");
		    	  key = sc.next();
		    	  System.out.println("Введите значение:");
				  value = sc.next();
				 FormatEnumAplha format =oper.checkInputAdd(key,value);
				 
				 switch(format){
					 case string:
					 if (enterNumber ==0){
					  System.out.println(alg.addKey(key, value,enterNumber));
						}
					 break;
					 
					 case integer:
					 if (enterNumber == 1){
					  System.out.println(alg.addKey(key, value,enterNumber));
						break;				  
						}					 
				 }
				 
				//  else {
				//	  if(enterNumber==0)
				//	  System.out.println("Ошибка, в данный словарь можно ввести только латинские буквы" +"\n"+"и не больше 4 символов");
				//	  else if (enterNumber==1)
				//		 System.out.println("Ошибка, в данный словарь можно ввести только цифры" +"\n"+"и не больше 5 цифр");
				//  }
				  }
			}
			
		  }
	}
	
}