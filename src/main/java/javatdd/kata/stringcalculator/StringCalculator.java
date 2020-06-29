package javatdd.kata.stringcalculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {

	public static int add(String string) throws Exception {
		if (string.isEmpty() || string == "") {
			return 0;
		} else if (string.contains("-")) {
			String negatives = "negatives not allowed : ";
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == '-') {
					negatives += string.charAt(i);
					while (i + 1 < string.length() && string.charAt(i + 1) != ',') {
						negatives += string.charAt(i + 1) + ", ";
						i++;
					}
					negatives += ", ";
					i++;
				}
			}
			negatives = negatives.substring(0, negatives.length() - 2);
			throw new Exception(negatives);
		} else if (string.contains(",") && string.length() == 3) {
			String[] num = string.split(",");
			return sumMultipleNum(string, ",");
		} else if (string.length() > 3 && !string.contains("//")) {
			return sumMultipleNum(string, ",|\n");
		} else if (string.startsWith("//")) {
			String pattern = "//(.)\n(.*)";
			Matcher matcher = Pattern.compile(pattern).matcher(string);
			matcher.matches();
			System.out.println("matcher.group(1): "+matcher.group(2));
			System.out.println("matcher.group(2): "+matcher.group(2));
			return sumMultipleNum(matcher.group(2), matcher.group(1));
		} else {
			return Integer.parseInt(string);
		}

	}

	public static int sumMultipleNum(String input, String pattern) {
		Stream<String> num = Arrays.stream(input.split(pattern));
		return num.mapToInt(Integer::parseInt).filter(n -> (n/1000 <=0)).sum();
	}
}
