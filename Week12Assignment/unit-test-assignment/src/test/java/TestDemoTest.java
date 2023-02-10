import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized.Parameter;

class TestDemoTest {
	private TestDemo testDemo;
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}
	@AfterEach
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared(5); 
		assertThat(fiveSquared).isEqualTo(25);
	}
	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		//Given 
		if(expectException) {
			  assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
			} else {
				assertThatThrownBy(() -> 
			    testDemo.addPositive(a, b))
			        .isInstanceOf(IllegalArgumentException.class);
			}
		

	
	}
	
	public static Stream<Arguments> argumentsForAddPositive() {
		return 
		Stream.of(
		Arguments.of(2, 4, 6, false),
		Arguments.of(2, 2, 4, false),
		Arguments.of(3, -5, -2, true));
	}
}
