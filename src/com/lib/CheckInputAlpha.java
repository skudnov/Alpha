package com.lib;
import org.springframework.context.annotation.*;

@Configuration
public class CheckInputAlpha implements ICheckInputAlpha{
	private String validationString = "[a-zA-Z]+";
	private String validationInt = "[0-9]+";
	CheckInputAlpha(){}
	
	@Bean
	public FormatEnumAplha checkInputAdd(String key){
		if(key.matches(validationString)){
				return FormatEnumAplha.string;
		}
		else if(key.matches(validationInt)) {
				return FormatEnumAplha.integer;
		}
			return FormatEnumAplha.mix;
	}
	
	@Bean
	public boolean checkLength(int stringKeyLength, String key) throws Exception{
		if (key.length()<=stringKeyLength)
			return true;
		else throw new Exception("Ключ не должен быть больше "+stringKeyLength+" символов ");
	}
	
} 