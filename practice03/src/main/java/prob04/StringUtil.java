package prob04;

public class StringUtil {
	private static String s = "";

	public static String concatenate(String[] strArr) {
		for(int i = 0; i < strArr.length; i++) {
			s += strArr[i];
		}
		return s;
	}
}
