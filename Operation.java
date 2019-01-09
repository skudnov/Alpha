import java.util.Map;
import java.util.Scanner;
import java.util.List;
public class Operation  implements OperationInterface {
	private CrudInterface alg;
	private String validationString = "[a-zA-Z]+";
	private String validationInt = "[0-9]+";
	Operation(CrudInterface alg)
	{
		this.alg=alg;
	}
	
	public Map<String,String> printAlpha() {
		return alg.getHashMap();
	}
	
	public List<String> getAlphaName(){
		return alg.getFileName();
	}
	
	
	
	
	public String getValue(String key) {
		
		if (alg.getKey(key)!=null)
			return("Ключ: "+ key +" Значение ключа: "+alg.getKey(key));
		else
			return("По данному ключу не обнаруженно значения");
	}
	
	public FormatEnumAplha checkInputAdd(String key,String value){
		if(key.matches(validationString)&&value.matches(validationString)){
			if (key.length()<=5&& value.length()<=5)
				return FormatEnumAplha.string;
		}
		else if(key.matches(validationInt)&&value.matches(validationInt)) {
			if (key.length()<=6&& value.length()<=6)
				return FormatEnumAplha.integer;
		}
			return FormatEnumAplha.mix;
	}
	
	public int scannerInt (Scanner sc){
		while (!sc.hasNextInt()) {
					System.out.println("Ошибка,введите число!");
					sc.next();
					}
					
					return sc.nextInt();
	}
	
	public boolean checkAplhaCount(){
		if (alg.getFileName().size()!=0)
			return true;
		else 
			return false;
		
	}
	
	
	
	
}