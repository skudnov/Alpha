public class CheckInputAlpha implements CheckInputAlphaInterface{
	private String validationString = "[a-zA-Z]+";
	private String validationInt = "[0-9]+";
	CheckInputAlpha(){}
	
	
	public FormatEnumAplha checkInputAdd(String key,String value){
		if(key.matches(validationString)&& value.matches(validationString)){
				return FormatEnumAplha.string;
		}
		else if(key.matches(validationInt)&&value.matches(validationInt)) {
				return FormatEnumAplha.integer;
		}
			return FormatEnumAplha.mix;
	}
	
	public boolean checkStringLength(int stringKeyLength, String key,String value){
		if (key.length()<=stringKeyLength && value.length()<=stringKeyLength)
			return true;
		else return false;
	}
	
	public boolean checkIntLength(int intKeyLength, String key,String value){
		if (key.length()<=intKeyLength && value.length()<=intKeyLength)
			return true;
		else return false;
	}
	
} 