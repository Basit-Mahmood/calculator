package pk.training.basit.service.impl;

import org.springframework.stereotype.Service;

import pk.training.basit.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public int sum(int number1, int number2) {
		return number1 + number2;
	}

}
