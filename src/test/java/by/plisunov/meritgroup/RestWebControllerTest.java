package by.plisunov.meritgroup;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import by.plisunov.meritgroup.controller.RestWebController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestWebController.class, secure = false)
public class RestWebControllerTest {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test rest-service with invalid request data. Checking only Rest Controller.
	 * 
	 * @expected HTTP 400 Code
	 * @throws Exception
	 */
	@Test
	public void validationTestWithBadRequest() throws Exception {

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/validate").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("dfds")).andReturn();

		assertEquals(400, result.getResponse().getStatus());
	}

	/**
	 * Test rest-service with valid data. Also testing TradeValidateService business
	 * logic. Test data and test results are located in files in application
	 * resources.
	 * 
	 * @input data from test.json in resources path
	 * @expected data from testResult.json in resources path
	 * @throws Exception
	 */
	@Test
	public void validationTest() throws Exception {

		String inputFileName = "test.json";
		String outputFileName = "testResult.json";
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File inputFile = new File(classLoader.getResource(inputFileName).getFile());
		File outputFile = new File(classLoader.getResource(outputFileName).getFile());
		String inputContent = new String(Files.readAllBytes(inputFile.toPath()));
		String outputContent = new String(Files.readAllBytes(outputFile.toPath()));
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/validate").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(inputContent)).andReturn();

		assertEquals(outputContent, result.getResponse().getContentAsString());
	}

}
