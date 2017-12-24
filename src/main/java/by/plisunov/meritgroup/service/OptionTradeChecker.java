package by.plisunov.meritgroup.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.plisunov.meritgroup.model.Trade;
import by.plisunov.meritgroup.model.TradeCheckResult;
import by.plisunov.meritgroup.util.TradeValidatorErrors;
import by.plisunov.meritgroup.util.TradeValidatorUtils;

/**
 * Implementation TradeChecker interface for Trade with type Optional. Extends
 * BasicTradeChecker.
 * 
 * @author Andrey
 *
 */
@Service
public class OptionTradeChecker extends BasicTradeChecker implements TradeChecker {

	@Autowired
	TradeValidatorUtils checks;

	@Override
	public TradeCheckResult check(Trade trade) {
		TradeCheckResult checkResult = super.check(trade);
		checkResult.setTrade(trade.toString());
		if (trade.getStyle().isPresent()) {
			if (!checks.checkStyle(trade.getStyle())) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.UNSUPPORTED_STYLE, trade.getStyle().get()));
			}
			if (!trade.getTradeDate().isPresent()) {
				checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "tradeDate"));
				if (!trade.getDeliveryDate().isPresent()) {
					checkResult.getErrors().get()
							.add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "deliveryDate"));
				}
				if (!trade.getPremiumDate().isPresent()) {
					checkResult.getErrors().get()
							.add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "premiumDate"));
				}
			} else if (!trade.getDeliveryDate().isPresent()) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "deliveryDate"));
				if (!trade.getPremiumDate().isPresent()) {
					checkResult.getErrors().get()
							.add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "premiumDate"));
				}
			} else if (!trade.getPremiumDate().isPresent()) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "premiumDate"));
			} else if (checks.checkDateBeforeDate(trade.getExpiryDate(), trade.getDeliveryDate())) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.EXPIRE_DATE_AFTER_DELIVERY_DATE,
								trade.getExpiryDate().get(), trade.getDeliveryDate().get()));
			}
			if (checks.checkDateBeforeDate(trade.getPremiumDate(), trade.getDeliveryDate())) {
				checkResult.getErrors().get()
						.add(MessageFormat.format(TradeValidatorErrors.PREMIUM_DATE_AFTER_DELIVERY_DATE,
								trade.getPremiumDate().get(), trade.getDeliveryDate().get()));
			}

		} else {
			checkResult.getErrors().get().add(MessageFormat.format(TradeValidatorErrors.MISSED_FIELD, "style"));
		}
		return checkResult;
	}

}
