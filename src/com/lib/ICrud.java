package com.lib;
import java.util.Map;
import java.util.List;
public interface ICrud	{
	public String openFile();
	
	public String addKey(String key,String value,int i);
	
	public String removeKey(String key,int i);
	
	public Map<String,String> getHashMap();
	
	public String getKey(String key);
	
	public List<String> getFileName();
}	