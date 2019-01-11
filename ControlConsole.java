
import java.util.Scanner;
import java.util.Map;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class ControlConsole{
	private String key,value,command;
	private int enterNumber;
	private OperationInterface oper;
	private PropertyResourceBundle myRes= null;
	ControlConsole(OperationInterface oper){
		this.oper = oper;
	}
	
	
	
	public void outputMenu(){
		
		
		System.out.println(oper.openFile());
		try {
	    myRes = new PropertyResourceBundle(new FileReader("res.properties"));
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		
		while(oper.checkAplhaCount()) {
			 System.out.println(myRes.getString(ConstRes.menu));
			 
				  command = oper.inputString();
		    switch(command) {
		      case "1": 
					System.out.println(myRes.getString(ConstRes.fullKey));
				  
					for (Map.Entry entry : oper.printAlpha().entrySet()) {
						System.out.println("Key: " + entry.getKey() + " Value: "
						+ entry.getValue());
					}
				  break;
			
			 case "2": 
		    	  System.out.println(myRes.getString(ConstRes.printKey));
		    	  key = oper.inputString();
		    	  System.out.println(oper.getValue(key));
		    	  break;
			case "3":
		    	  System.out.println(myRes.getString(ConstRes.printKey));
		    	  key = oper.inputString();
		    	  System.out.println(myRes.getString(ConstRes.printAlpha));
				  int count =0;
				  for(Object nameFile : oper.getAlphaName()){
					  System.out.println(count+": "+ nameFile);
					  count++;
				  }	
						while (true) {
						try{
                            enterNumber = oper.scannerInt(count);
						    break;
                        }
                        catch (Exception e){
						    System.out.println(e.getMessage());

                        }

					}
					
		    	  System.out.println(oper.removeKey(key,enterNumber));
		    	  break;
		    	 
			case "4":
				System.out.println(myRes.getString(ConstRes.fullKey));
				  count =0;
				  for(Object nameFile : oper.getAlphaName()){
					  System.out.println(count+": "+ nameFile);
					  count++;
				  }
				  while (true) {
						try{
                            enterNumber = oper.scannerInt(count);
						    break;
                        }
                        catch (Exception e){
						    System.out.println(e.getMessage());

                        }

					}
				  
				  while (true) {
				  System.out.println(myRes.getString(ConstRes.printKey));
		    	  key = oper.inputString();
		    	  System.out.println(myRes.getString(ConstRes.printValue));
				  value = oper.inputString();
				  System.out.println(oper.addKey(key, value,enterNumber));
					break;
				  }
				  break;
			default: System.out.println(myRes.getString(ConstRes.errorCommand));
			}
          
		  }
	}
	
}