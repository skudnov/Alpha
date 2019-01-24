package lib;
import java.util.Map;
import java.util.List;
public interface ICrud {
	String read();

	String create(String key, String value, int i);

	String delete(String key, int i);

	String update(String key, String value, int i);

	Map<String, List<String>> getAlphabet();

	List<String> getValue(String key);

	List<String> getAlphabetNames();


}