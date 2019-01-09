import java.util.Scanner;
import java.util.Map;
import java.util.List;
public interface OperationInterface	{
	
public String getValue(String key);

public FormatEnumAplha checkInputAdd(String key,String value);

public int scannerInt (Scanner sc);

public Map<String,String> printAlpha();

public boolean checkAplhaCount();

public List<String> getAlphaName();
}