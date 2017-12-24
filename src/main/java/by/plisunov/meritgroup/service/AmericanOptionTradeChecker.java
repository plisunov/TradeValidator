package by.plisunov.meritgroup.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.plisunov.meritgroup.model.Trade;
import by.plisunov.meritgroup.model.TradeCheckResult;
import by.plisunov.meritgroup.util.TradeValidatorErrors;
import by.plisunov.meritgroup.util.TradeValidatorUtils;

/**
 * Implementation TradeChecker interface for Trade with type Optional and style
 * American. Extends OptionTradeChecker.
 * 
 * @author Andrey
 *
 */
@Service
public class AmericanOptionTradeChecker extends OptionTradeChecker implements TradeChecker {
	@Autowired
	TradeValidatorUtils checks;

	@Override
	public TradeCheckResult check(Trade trade) {
		TradeCheckResult checkResult = super.check(trade);
		checkResult.setTrade(trade.toString());
		if (!trade.getTradeDate().isPresent()) {
			checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "tradeDate"));
			if (!trade.getExpiryDate().isPresent()) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "expireDate"));
			}
			if (!trade.getExcerciseStartDate().isPresent()) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "excerciseStartDate"));
			}
		} else if (!trade.getExpiryDate().isPresent()) {
			checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "expireDate"));
			if (!trade.getExcerciseStartDate().isPresent()) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "excerciseStartDate"));
			}
		} else if (!trade.getExcerciseStartDate().isPresent()) {
			checkResult.getErrors().get()
					.add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "excerciseStartDate"));
		} else {
			if (checks.checkDateBeforeDate(trade.getTradeDate(), trade.getExcerciseStartDate())) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.EXCERCISE_DATE_BEFORE_TRADE_DATE,
								trade.getExcerciseStartDate().get(), trade.getTradeDate().get()));
			}
			if (checks.checkDateBeforeDate(trade.getExcerciseStartDate(), trade.getExpiryDate())) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.EXCERCISE_DATE_AFTER_EXPIRE_DATE,
								trade.getExcerciseStartDate().get(), trade.getExpiryDate().get()));
			}
		}
		return checkResult;
	}

}
