package lib;

import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.*;

@Configuration
public class Crud implements ICrud {

	private Map<String, List<String>> alpha = new HashMap<>();
	private Map<String, List<String>> fileNew = new HashMap<>();
	private List<String> ListFile = new ArrayList<>();
	private String fileName;
	private String directory = "fileAlpha";

	private String getFileExtension(File fullName) {
		fileName = fullName.getName();
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex == -1)
			return "zero";
		else
			return fileName.substring(dotIndex);
	}

	@Override
	public String read() {
		try {
			File dir = new File(".//" + directory);

			if (dir.isDirectory()) {
				if (Objects.requireNonNull(dir.listFiles()).length != 0) {
					for (File item : Objects.requireNonNull(dir.listFiles())) {
						if (getFileExtension(item).equals(".txt")) {
							fileName = item.getName();
							ListFile.add(fileName);

							loadAlphaBetValue(fileName);

						}

						alpha.putAll(fileNew);
						fileNew.clear();
					}
					return "Словари успешно загруженны";
				} else return "В данном каталоге нет файлов удовлетроворющих условию (.txt)";
			} else return ("Не является директорией");

		} catch (IOException ex) {

			return ex.getMessage();
		}


	}


	private void loadAlphaBetValue(String fileName) throws IOException {


		FileInputStream file = new FileInputStream(directory + "//" + fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(file));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			String[] count = strLine.split(" ");
			List<String> valueStringList = new ArrayList<>();
			if (count.length == 2) {
				valueStringList.add(count[1]);
				fileNew.put(count[0], valueStringList);
			}
		}

	}

	@Override
	public List<String> getAlphabetNames() {
		return ListFile;
	}

	@Override
	public Map<String, List<String>> getAlphabet() {
		return new HashMap<>(alpha);
	}


	@Override
	public List<String> getValue(String key) {
		if (alpha.get(key) != null) {
			return alpha.get(key);
		}
		else
			return null;
	}


	@Override
	public String create(String key, String value, int i) {

		try {
			if (!alpha.containsKey(key)) {
				fileName = ListFile.get(i);
				BufferedWriter writer = new BufferedWriter(new FileWriter(directory + "//" + fileName, true));
				writer.write(key + " " + value + "\n");
				writer.flush();
				writer.close();
				List<String> valueStringList = new ArrayList<>();
				valueStringList.add(value);
				alpha.put(key, valueStringList);
				return ("Данные успешно добавлены");
			} else return ("Данный ключ уже существует");


		} catch (Exception e) {
			return ("Ошибка при добавлении ключа");
		}
	}

	@Override
	public String delete(String key, int i) {

		try {
			fileName = ListFile.get(i);
			fileNew.clear();
			loadAlphaBetValue(fileName);

			if (fileNew.get(key) != null) {
				FileWriter writer = new FileWriter(directory + "//" + fileName, false);
				fileNew.remove(key);
				alpha.remove(key);
				for (Map.Entry entry : fileNew.entrySet()) {
					writer.write(entry.getKey() + " " + entry.getValue());
					writer.append('\n');

				}
				writer.flush();
				return ("Успешно удалено");
			} else return ("Данный ключ не обнаружен");
		} catch (Exception e) {
			return ("Ошибка при удалении ключа");
		}
	}

	@Override
	public String update(String key, String value, int i) {
		return null;
	}

}