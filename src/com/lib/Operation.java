package com.lib;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class Operation  implements IOperation {
	@Autowired
	private ICrud alg;
	@Autowired
	private ICheckInputAlpha input;
	private static Scanner sc = new Scanner(System.in);
	Operation()	{	}
	
	@Bean
	public Map<String,String> printAlpha() {
		return alg.getHashMap();
	}
	
	@Bean
	public List<String> getAlphaName(){
		return alg.getFileName();
	}
	
	@Bean
	public String openFile(){
		return alg.openFile();
	}
	
	@Bean
	public String removeKey(String key,int i){
		return alg.removeKey(key,i);
	}
	
	@Bean
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

	
	
	@Bean
	public String getValue(String key) {
		
		if (alg.getKey(key)!=null)
			return("Ключ: "+ key +" Значение ключа: "+alg.getKey(key));
		else
			return("По данному ключу не обнаруженно значения");
	}
	
	@Bean
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
	
	@Bean
	public String inputString(){
		String inputstring = sc.next();
		return  inputstring;

	}
	@Bean
	public boolean checkAplhaCount(){
		if (alg.getFileName().size()!=0)
			return true;
		else 
			return false;
		
	}
	
	
	
	
}