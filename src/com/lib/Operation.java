package lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	private CheckInputAlpha input;

	private static Scanner sc = new Scanner(System.in);

	Operation() {}

	@Override
	public Map<String, List<String>> getAlphabet() {
		return alg.getAlphabet();
	}

	@Override
	public List<String> getAlphabetNames() {
		return alg.getAlphabetNames();
	}

	@Override
	public String read() {
		return alg.read();
	}

	@Override
	public String remove(String key, int i) {
		return alg.delete(key, i);
	}

	@Override
	public String update(String key, String value, int i) {
		try {
			FormatEnumAha format = input.checkInputAdd(key);
			if (i == 0) {
				if (format == FormatEnumAha.string) {
					if (input.checkLength(5, key)) {
						return alg.update(key, value, i);
					}
				} else {
					return "Данный словарь является строковым";
				}
			}

			return "Ошибка,Данные не добавлены";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String create(String key, String value, int i) {
		try {
			FormatEnumAha format = input.checkInputAdd(key);
			if (i == 0) {
				if (format == FormatEnumAha.string) {
					if (input.checkLength(5, key)) {
						return alg.create(key, value, i);
					}
				} else {
					return "Данный словарь является строковым";
				}
			}

			return "Ошибка,Данные не добавлены";
		} catch (Exception e) {
			return e.getMessage();
		}
	}


	@Override
	public String getValue( String key) {

		String getStringValue="Ключ: " + key + " Значение ключа: " ;
		if (alg.getValue(key) != null) {
			for (String list: alg.getValue(key)) {
				getStringValue +=(list + " ");
			}
			return getStringValue;
		}
		else
			return ("По данному ключу не обнаруженно значения");
	}

	@Override
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

	@Override
	public String inputString() {
		return sc.next();
	}

	@Override
	public boolean getAlphabetCount() {
		return alg.getAlphabetNames().size() != 0;

	}


}