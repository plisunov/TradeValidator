package by.plisunov.meritgroup.controller;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.plisunov.meritgroup.model.Trade;
import by.plisunov.meritgroup.service.TradeValidateService;
import by.plisunov.meritgroup.service.TradeValidateServiceImpl;

/**
 * Basic spring rest controller for REST service
 * 
 * @author Andrey
 *
 */
@RestController
@RequestMapping("/")
public class RestWebController {

	@Autowired
	private TradeValidateService tradeValidateService;

	@Autowired
	private ObjectMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(TradeValidateServiceImpl.class);

	/**
	 * Validate service endpoint
	 * 
	 * @param request
	 *            - Json arrays of trades.
	 * @return Json with results info or BadRequest(400) or Internal Server Error
	 *         (500)
	 */
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public @ResponseBody Object validateTrades(@RequestBody final String request) {
		try {
			Trade[] tradeListAsArray = mapper.readValue(request, Trade[].class);
			return mapper.writeValueAsString(tradeValidateService.validateTradeList(Arrays.asList(tradeListAsArray)));
		} catch (JsonParseException e) {
			logger.debug("JSON parsin error " + e.getStackTrace().toString());
			logger.debug("JSON: " + request);
			return ResponseEntity.badRequest().build();
		} catch (JsonMappingException e) {
			logger.debug("JSON parsin to object error " + e.getStackTrace().toString());
			logger.debug("JSON: " + request);
			return ResponseEntity.badRequest().build();
		} catch (IOException e) {
			logger.debug("JSON file error " + e.getStackTrace().toString());
			logger.debug("JSON: " + request);
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			logger.debug("Application error " + e.getStackTrace().toString());
			return ResponseEntity.status(500).build();
		}
	}
}