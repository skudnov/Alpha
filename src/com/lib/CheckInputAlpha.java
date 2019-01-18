package lib;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckInputAlpha implements ICheckInputAlpha {

	CheckInputAlpha() {
	}

	@Bean
	public FormatEnumAha checkInputAdd(@Qualifier("addKey") String key) {
		String validationString = "[a-zA-Z]+";
		String validationInt = "[0-9]+";
		if (key.matches(validationString)) {
			return FormatEnumAha.string;
		} else if (key.matches(validationInt)) {
			return FormatEnumAha.integer;
		}
		return FormatEnumAha.mix;
	}

	@Bean
	public boolean checkLength(int stringKeyLength, @Qualifier("addKey") String key) throws Exception {
		if (key.length() <= stringKeyLength)
			return true;
		else throw new Exception("Ключ не должен быть больше " + stringKeyLength + " символов ");
	}

}