package zerobase.weather;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class WeatherApplicationTests {


	@Test
	void equalTest() {

		assertEquals(1, 1);
	}

	@Test
	void nullTest(){
		assertNull(null);
	}

	@Test
	@DisplayName("참 거짓 테스트")
	void trueTest(){
		assertTrue(1==1);
	}
}
