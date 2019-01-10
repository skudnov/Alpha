import java.util.Scanner;
import java.util.Map;
import java.util.List;
public interface OperationInterface	{
	
public String getValue(String key);

public int scannerInt (Scanner sc);

public Map<String,String> printAlpha();

public boolean checkAplhaCount();

public List<String> getAlphaName();

public String openFile();

public String addKey(String key,String value,int i);

public String removeKey(String key,int i);
}