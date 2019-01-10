import java.util.Map;
import java.util.Scanner;
import java.util.List;
public class Operation  implements OperationInterface {
	private CrudInterface alg;
	private CheckInputAlpha input = new CheckInputAlpha();
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
		FormatEnumAplha format =input.checkInputAdd(key,value);
		if (format == FormatEnumAplha.string){
			if (input.checkStringLength(5,key,value)){ 
			if (i!=0){
				return "Данный словарь не является строковым";
			}
			}
			else {
				return "Ошибка, ключ должен иметь длину не больше 5";
			}
		
		}
		else if(format == FormatEnumAplha.integer){
				if (input.checkStringLength(6,key,value)){
				if (i!=1){
					return "Данный словарь не является цифровым";
				}
				}
				else {
					return "Ошибка, ключ должен иметь длину не больше 6";
				}
			}
			return alg.addKey(key,value,i);
	}

	
	
	
	public String getValue(String key) {
		
		if (alg.getKey(key)!=null)
			return("Ключ: "+ key +" Значение ключа: "+alg.getKey(key));
		else
			return("По данному ключу не обнаруженно значения");
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