package com.lib;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.*;

@Configuration
public class CrudAlgoritm implements ICrud{
	
	private Map<String,String> alpha = new HashMap<String,String>();
	private Map<String,String> fileNew = new HashMap<String,String>();
	private BufferedReader br;
	private List<String> ListFile = new ArrayList<String>();
	private String fileName;
	private String directory = "fileAlpha";	
	
	@Bean
	public String getFileExtension(File fullName) {
    fileName = fullName.getName();
    int dotIndex = fileName.lastIndexOf('.');
	if (dotIndex == -1)
		return "zero";
	else
    return fileName.substring(dotIndex);
	}
	
	@Bean
	public String openFile() {
		try
				{
			File dir = new File(".//"+directory);
			
			if(dir.isDirectory())
         {
            if (dir.listFiles().length!=0 ){
            for(File item : dir.listFiles()){
			 
             
			 if (getFileExtension(item).equals(".txt")){
				 fileName = item.getName();
                 ListFile.add(fileName);
				 
				 loadHash(fileName);
				 
			 } 
             
			 alpha.putAll(fileNew);
			 fileNew.clear();
			}
			return "Словари успешно загруженны";
			}
			else return "В данном каталоге нет файлов удовлетроворющих условию (.txt)";
         }
		 else return ("Не является директорией"); 
		 
		 }
        catch(IOException ex){
             
            return ex.getMessage();
        } 
		
		
	}
	
	
	public void loadHash(String fileName) throws IOException {
		
			
		FileInputStream fstream = new FileInputStream(directory+"//"+fileName);
				br = new BufferedReader(new InputStreamReader(fstream));
				String strLine;
				while ((strLine = br.readLine()) != null){
				   String[] count = strLine.split(" ");
				   if (count.length==2){
				   fileNew.put(count[0], count[1]);				   
				   }
				}
				
	}
	
	@Bean
	public List<String> getFileName(){
	return ListFile;
	}
	
	@Bean
	public Map<String,String> getHashMap(){
		
	return new HashMap<String,String>(alpha);
	}
	
	
	@Bean
	public String getKey(String key)
	{
		if (alpha.get(key)!=null)
			return alpha.get(key);
		else
			return null;
	}
	
	
	@Bean
	public String addKey(String key,String value,int i) {
		
		try {
			if (!alpha.containsKey(key)) {			
			fileName = ListFile.get(i);
			BufferedWriter writer = new BufferedWriter(new FileWriter(directory+"//"+fileName, true));
			writer.write(key+" "+value+"\n");			
			writer.flush();
			writer.close();
			alpha.put(key, value);
			return("Данные успешно добавлены");
			}
			else return("Данный ключ уже существует");
			
			
		} 
		catch (Exception e) {
			return("Ошибка при добавлении ключа");
		}
	}
	
	@Bean
	public String removeKey(String key,int i) {
		
			try
	        {
				fileName = ListFile.get(i);
				fileNew.clear();
				loadHash(fileName);
				
				if (fileNew.get(key)!=null) {
					FileWriter writer = new FileWriter(directory+"//"+fileName, false);
					fileNew.remove(key);
					alpha.remove(key);
				for (Map.Entry entry : fileNew.entrySet()) {
				    writer.write(entry.getKey()+" "+entry.getValue());
					writer.append('\n');
					
				}
				 writer.flush();
				return("Успешно удалено");
				}
				else return("Данный ключ не обнаружен");
	        }
	        catch(Exception e){
	            return("Ошибка при удалении ключа");
	        }
	}
	
}