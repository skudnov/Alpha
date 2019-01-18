package lib;
import java.util.Map;
import java.util.List;
public interface ICrud {
	String openFile();

	String addKey(String key, String value, int i);

	String removeKey(String key, int i);

	Map<String, String> getHashMap();

	String getValue(String key);

	List<String> getFileName();
}