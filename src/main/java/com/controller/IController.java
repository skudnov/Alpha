package com.controller;


import java.util.Map;
import java.util.List;
public interface IController {

    String getValue(String key);

    int scannerInt(int count) throws Exception;

    Map<String, List<String>> getAlphabet();

    boolean getAlphabetCount();

    List<String> getAlphabetNames();

    String read();

    String create(String key, String value, int i);

    String remove(String key, int i);

    String update(String key,String value,int i);

    String inputString();
}
