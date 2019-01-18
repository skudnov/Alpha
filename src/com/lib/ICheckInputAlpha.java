package lib;
public interface ICheckInputAlpha {
	FormatEnumAha checkInputAdd(String key);

	boolean checkLength(int intKeyLength, String key) throws Exception;
}