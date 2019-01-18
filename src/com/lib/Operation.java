package lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Configuration
public class Operation  implements IOperation {

	@Autowired
	@Qualifier("crud")
	private ICrud alg;

	@Autowired
	@Qualifier("input")
	private ICheckInputAlpha input;

	private static Scanner sc = new Scanner(System.in);

	Operation() {}

	@Bean
	public Map<String, String> printAlpha() {
		return alg.getHashMap();
	}

	@Bean
	public List<String> getAlphaName() {
		return alg.getFileName();
	}

	@Bean
	public String openFile() {
		return alg.openFile();
	}

	@Bean
	public String removeKey(@Qualifier("removeKey") String key, int i) {
		return alg.removeKey(key, i);
	}

	@Bean
	public String addKey(@Qualifier("addKey") String key,@Qualifier("addKey") String value, int i) {
		try {
			FormatEnumAha format = input.checkInputAdd(key);
			if (i == 0) {
				if (format == FormatEnumAha.string) {
					if (input.checkLength(5, key)) {

						return alg.addKey(key, value, i);

					}
				} else {
					return "Данный словарь является строковым";
				}


			} else if (i == 1) {
				if (format == FormatEnumAha.integer) {
					if (input.checkLength(6, key)) {

						return alg.addKey(key, value, i);
					}
				} else {
					return "Данный словарь является цифровым";
				}


			}

			return "Ошибка,Данные не добавлены";
		} catch (Exception e) {
			return e.getMessage();
		}
	}


	@Bean
	public String getValue(@Qualifier("getValue") String key) {

		if (alg.getValue(key) != null)
			return ("Ключ: " + key + " Значение ключа: " + alg.getValue(key));
		else
			return ("По данному ключу не обнаруженно значения");
	}

	@Bean
	public int scannerInt(int count) throws Exception {
		if (sc.hasNextInt()) {
			int check = sc.nextInt();
			if (check >= 0 && check < count) {
				return check;
			} else
				throw new Exception("Ошибка,такого словаря нет,повторите попытку");
		} else {
			sc.next();
			throw new Exception("Ошибка,введите число!");
		}
	}

	@Bean
	public String inputString() {
		return sc.next();
	}

	@Bean
	public boolean checkAlphaCount() {
		return alg.getFileName().size() != 0;

	}


}