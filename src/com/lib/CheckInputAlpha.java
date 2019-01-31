package com.lib;
public class CheckInputAlpha implements ICheckInputAlpha{
	private String validationString = "[a-zA-Z]+";
	private String validationInt = "[0-9]+";
	CheckInputAlpha(){}
	
	
	public FormatEnumAplha checkInputAdd(String key){
		if(key.matches(validationString)){
				return FormatEnumAplha.string;
		}
		else if(key.matches(validationInt)) {
				return FormatEnumAplha.integer;
		}
			return FormatEnumAplha.mix;
	}
	
	public boolean checkLength(int stringKeyLength, String key) throws Exception{
		if (key.length()<=stringKeyLength)
			return true;
		else throw new Exception("Ключ не должен быть больше "+stringKeyLength+" символов ");
	}
	
} 