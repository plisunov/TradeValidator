package by.plisunov.meritgroup.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.plisunov.meritgroup.model.Trade;
import by.plisunov.meritgroup.model.TradeCheckResult;
import by.plisunov.meritgroup.util.TradeValidatorErrors;
import by.plisunov.meritgroup.util.TradeValidatorUtils;

/**
 * Implementation TradeChecker interface for Trade with type Spot and Forward.
 * Extends Basic TradeChecker.
 * 
 * @author Andrey
 *
 */
@Service
public class SpotForwardTradeChecker extends BasicTradeChecker implements TradeChecker {

	@Autowired
	TradeValidatorUtils checks;

	@Override
	public TradeCheckResult check(Trade trade) {
		TradeCheckResult checkResult = super.check(trade);
		checkResult.setTrade(trade.toString());
		if (!trade.getValueDate().isPresent()) {
			checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "valueDate"));
			if (!trade.getTradeDate().isPresent()) {
				checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "tradeDate"));
			}
		} else if (!trade.getTradeDate().isPresent()) {
			checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "tradeDate"));
		} else {
			if (!checks.checkDateBeforeDate(trade.getValueDate(), trade.getTradeDate())) {
				checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.VALUEDATE_BEFORE_TRADEDATE,
						trade.getValueDate().get(), trade.getTradeDate().get()));
			}
			if (checks.checkWeekend(trade.getValueDate())) {
				checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.VALUEDATE_FALL_ON_WEEKEND,
						trade.getValueDate().get()));
			}
		}
		return checkResult;
	}

}
