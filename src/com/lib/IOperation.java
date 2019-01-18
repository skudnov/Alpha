package lib;

import java.util.Map;
import java.util.List;
public interface IOperation {

    String getValue(String key);

    int scannerInt(int count) throws Exception;

    Map<String, String> printAlpha();

    boolean checkAlphaCount();

    List<String> getAlphaName();

    String openFile();

    String addKey(String key, String value, int i);

    String removeKey(String key, int i);

    String inputString();
}