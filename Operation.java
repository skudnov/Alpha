import java.util.Map;
import java.util.Scanner;
import java.util.List;
public class Operation  implements OperationInterface {
	private CrudInterface alg;
	private CheckInputAlphaInterface input = new CheckInputAlpha();
	private static Scanner sc = new Scanner(System.in);
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
	
	public String openFile(){
		return alg.openFile();
	}
	
	public String removeKey(String key,int i){
		return alg.removeKey(key,i);
	}
	
	public String addKey(String key,String value,int i){
		try{
		FormatEnumAplha format =input.checkInputAdd(key);
		if (i==0){
			if (format == FormatEnumAplha.string){
			if (input.checkLength(5,key)){ 
			
				return alg.addKey(key,value,i);
			
			}
			}
			else{
				return "Данный словарь является строковым";
			}
				
		
		}
		else if (i==1){
			if (format == FormatEnumAplha.integer){
			if (input.checkLength(6,key)){ 
			
				return alg.addKey(key,value,i);
			}
			}
			else{
				return "Данный словарь является цифровым";
			}
				
		
		}
			
			return "Ошибка,Данные не добавлены";
		}
		catch(Exception e){
			return e.getMessage();
		}
	}

	
	
	
	public String getValue(String key) {
		
		if (alg.getKey(key)!=null)
			return("Ключ: "+ key +" Значение ключа: "+alg.getKey(key));
		else
			return("По данному ключу не обнаруженно значения");
	}
	
	public int scannerInt(int count) throws Exception {
		if (sc.hasNextInt()) {
			int check= sc.nextInt();
			if (check >= 0 && check < count) {
				return check;
			} else
				throw new Exception("Ошибка,такого словаря нет,повторите попытку");
		}
		else
		{
			sc.next();
			throw new Exception("Ошибка,введите число!");
		}
	}
	
	public String inputString(){
		String s = sc.next();
		return  s;

	}
	
	public boolean checkAplhaCount(){
		if (alg.getFileName().size()!=0)
			return true;
		else 
			return false;
		
	}
	
	
	
	
}