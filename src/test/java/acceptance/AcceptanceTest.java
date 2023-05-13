package acceptance;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/** 
 * Acceptance Test<br>
 * This is the entry point to the acceptance test suite.
 */ 
@RunWith(Cucumber.class) 
@CucumberOptions(features = "classpath:feature")
public class AcceptanceTest {

}
