package by.plisunov.meritgroup.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.plisunov.meritgroup.model.Trade;
import by.plisunov.meritgroup.model.TradeCheckResult;
import by.plisunov.meritgroup.util.TradeValidatorErrors;
import by.plisunov.meritgroup.util.TradeValidatorUtils;

/**
 * Implementation TradeChecker interface for Trade with All types. Contains
 * basic checks.
 * 
 * @author Andrey
 *
 */
@Service
public class BasicTradeChecker implements TradeChecker {

	@Autowired
	TradeValidatorUtils checks;

	@Override
	public TradeCheckResult check(Trade trade) {
		TradeCheckResult checkResult = new TradeCheckResult();
		checkResult.setTrade(trade.toString());
		if (trade.getCcyPair().isPresent()) {
			if (!checks.checkCurrency(trade.getCcyPair())) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.INVALID_CURRENCY, trade.getCcyPair().get()));
			}
		} else {
			checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "ccyPair"));
		}
		if (trade.getCustomer().isPresent()) {
			if (!checks.checkCustomer(trade.getCustomer())) {
				checkResult.getErrors().get().add(
						MessageFormat.format(TradeValidatorErrors.UNSUPPORTED_CUSTOMER, trade.getCustomer().get()));
			}
		} else {
			checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "customer"));
		}
		if (trade.getLegalEntity().isPresent()) {
			if (!checks.checkLegalEntity(trade.getLegalEntity())) {
				checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.UNSUPPORTED_LEGAL_ENTITY,
						trade.getLegalEntity().get()));
			}
		} else {
			checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "legalEntity"));
		}

		return checkResult;
	}

}
