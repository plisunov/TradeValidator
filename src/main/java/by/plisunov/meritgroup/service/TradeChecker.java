package by.plisunov.meritgroup.service;

import by.plisunov.meritgroup.model.Trade;
import by.plisunov.meritgroup.model.TradeCheckResult;

/**
 * Main Interface for check trade
 * 
 * @author Andrey
 *
 */
public interface TradeChecker {
	/**
	 * Method for check Trade
	 * 
	 * @param Trade
	 *            trade
	 * @return TradeCheckResult
	 */
	public TradeCheckResult check(Trade trade);

}
