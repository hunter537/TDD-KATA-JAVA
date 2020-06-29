package javatdd.kata.stringcalculator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringCaculatorTest {

	@Test
	public void validateAdd_returnZero() throws Exception {
		assertThat(StringCalculator.add(""), is(0));
	}

	@Test
	public void validateAdd_returnInput() throws Exception {
		assertThat(StringCalculator.add("1"), is(1));
	}

	@Test
	public void validateAdd_returnSumforTwoNumbers() throws Exception {
		assertThat(StringCalculator.add("1,2"), is(3));
	}

	@Test
	public void validateAdd_returnSumforMoreThanTwoNumbers() throws Exception {
		assertThat(StringCalculator.add("1,2,3,4"), is(10));
	}

	@Test
	public void validateAdd_returnSumforNewLine() throws Exception {
		assertThat(StringCalculator.add("1,2\n3,4\n5"), is(15));
	}

	@Test
	public void validateAdd_returnSumforDefaultDelimeter() throws Exception {
		assertThat(StringCalculator.add("//;\n11;2"), is(13));
	}

	@Test
	public void validateAdd_returnSumforDelimeter() throws Exception {
		assertThat(StringCalculator.add("//@\n11@2"), is(13));
	}

	@Test
	public void validateAdd_negativeValueException() throws Exception {
		try {
			String string = "1,-5,-6,2,3,-4,4,5,-188";
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

			Exception exception = assertThrows(Exception.class, () -> {
				StringCalculator.add(string);
			});
			assertTrue(exception.getMessage().contains(negatives));
		} catch (RuntimeException e) {
			System.out.println("negatives not allowed");
			e.printStackTrace();
		}
	}
	
	@Test
	public void validateAdd_ignoreIfGreaterThanThousand() throws Exception {
		assertThat(StringCalculator.add("1,2,3,10000"), is(6));
	}
}
