package acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Each line (Given, When, and Then) from the feature specification file is matched by regular expressions (regexes) with the corresponding
 * method in the Java code. 
 */
public class StepDefinitions {

	// Note that the server address is passed as the calculator.url Java property
	private String server = System.getProperty("calculator.url");

	private RestTemplate restTemplate = new RestTemplate();

	private String a;
	private String b;
	private String result;

	/**
	 * i_have_two_numbers: Saves parameters as fields<br>
	 * Wildcards (.*) are passed as parameters.
	 */
	@Given("^I have two numbers: (.*) and (.*)$")
	public void i_have_two_numbers(String a, String b) throws Throwable {
		this.a = a;
		this.b = b;
	}

	/**
	 * the_calculator_sums_them: Calls the remote calculator service and stores the result in a field
	 */
	@When("^the calculator sums them$")
	public void the_calculator_sums_them() throws Throwable {
		String url = String.format("%s/sum?num1=%s&num2=%s", server, a, b);
		result = restTemplate.getForObject(url, String.class);
	}

	/**
	 * i_receive_as_a_result: Asserts that the result is as expected<br>
	 * Wildcards (.*) are passed as parameters.
	 */
	@Then("^I receive (.*) as a result$")
	public void i_receive_as_a_result(String expectedResult) throws Throwable {
		assertEquals(expectedResult, result);
	}

}
