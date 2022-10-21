package pk.training.basit.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pk.training.basit.service.CalculatorService;

@RestController
public class CalculatorRestController {

	private final CalculatorService calculatorService;

	public CalculatorRestController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@GetMapping("/sum")
	String sum(@RequestParam("num1") Integer number1, @RequestParam("num2") Integer number2) {
		return String.valueOf(calculatorService.sum(number1, number2));
	}

}
