package by.plisunov.meritgroup.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.plisunov.meritgroup.model.Trade;
import by.plisunov.meritgroup.model.TradeCheckResult;
import by.plisunov.meritgroup.util.TradeValidatorConstants;
import by.plisunov.meritgroup.util.TradeValidatorErrors;

@Service
public class TradeValidateServiceImpl implements TradeValidateService {

	/**
	 * List of all implementation for TradeChecker interface.
	 */
	@Autowired
	private List<TradeChecker> checkers;

	private static final Logger logger = LoggerFactory.getLogger(TradeValidateServiceImpl.class);

	@Override
	public List<TradeCheckResult> validateTradeList(List<Trade> tradeList) {
		long tBegin = System.currentTimeMillis();
		logger.debug("Start method validateTradeList(). tradeList.size()=" + tradeList.size());
		List<TradeCheckResult> result = new ArrayList<>();
		for (Trade trade : tradeList) {
			result.add(validateTrade(trade));
		}
		long tFinish = System.currentTimeMillis();
		logger.debug("Finish method validateTradeList(). Time =" + (tFinish - tBegin) + " ms.");
		return result;
	}

	@Override
	public TradeCheckResult validateTrade(Trade trade) {
		TradeCheckResult checkResult = new TradeCheckResult();
		checkResult.setTrade(trade.toString());
		TradeChecker checker;
		if (trade.getType().isPresent()) {
			if ((TradeValidatorConstants.TRADE_SPOT_TYPE.equals(trade.getType().get().trim().toUpperCase()))
					|| (TradeValidatorConstants.TRADE_FORWARD_TYPE
							.equals(trade.getType().get().trim().toUpperCase()))) {
				checker = checkers.stream().filter(s -> s.getClass().getSimpleName().equals("SpotForwardTradeChecker"))
						.collect(Collectors.toList()).get(0);
				checkResult = checker.check(trade);
			} else if (trade.getType().get().trim().toUpperCase()
					.indexOf(TradeValidatorConstants.TRADE_ANY_OPTION_TYPE) > -1) {
				if (TradeValidatorConstants.AMERICAN_STYLE.equals(trade.getStyle().get().trim().toUpperCase())) {
					checker = checkers.stream()
							.filter(s -> s.getClass().getSimpleName().equals("AmericanOptionTradeChecker"))
							.collect(Collectors.toList()).get(0);
					checkResult = checker.check(trade);
				} else {
					checker = checkers.stream().filter(s -> s.getClass().getSimpleName().equals("OptionTradeChecker"))
							.collect(Collectors.toList()).get(0);
					checkResult = checker.check(trade);
				}
			} else {
				checker = checkers.stream().filter(s -> s.getClass().getSimpleName().equals("BasicTradeChecker"))
						.collect(Collectors.toList()).get(0);
				checkResult = checker.check(trade);
			}
		} else {
			checker = checkers.stream().filter(s -> s.getClass().getSimpleName().equals("BasicTradeChecker"))
					.collect(Collectors.toList()).get(0);
			checkResult = checker.check(trade);
			checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "type"));
		}
		if (checkResult.getErrors().get().isEmpty()) {
			checkResult.setStatus(TradeValidatorConstants.TRADE_OK);
		} else {
			checkResult.setStatus(TradeValidatorConstants.TRADE_FAIL);
		}
		return checkResult;
	}
}
