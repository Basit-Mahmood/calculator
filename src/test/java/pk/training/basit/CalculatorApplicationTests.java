package pk.training.basit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pk.training.basit.service.CalculatorService;
import pk.training.basit.service.impl.CalculatorServiceImpl;

public class CalculatorApplicationTests {

	private CalculatorService calculatorService = new CalculatorServiceImpl();
	
	@Test
	public void testSum() {
		assertEquals(5, calculatorService.sum(2, 3));
	}

}
