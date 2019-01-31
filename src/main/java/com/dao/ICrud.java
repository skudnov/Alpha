package com.dao;

import java.util.Map;
import java.util.List;
public interface ICrud {
    String read();

    String create(String key, String value, String idGroup);

    String delete(String key,String idGroup);

    String update(String key, String value,String idGroup);

    Map<String, List<String>> getAlphabet();

    List<String> getValue(String key);

    List<String> getAlphabetNames();


}
