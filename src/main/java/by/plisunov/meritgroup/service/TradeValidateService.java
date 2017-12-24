package by.plisunov.meritgroup.service;

import java.util.List;

import by.plisunov.meritgroup.model.Trade;
import by.plisunov.meritgroup.model.TradeCheckResult;

public interface TradeValidateService {
	/**
	 * Validate list of trades
	 * 
	 * @param List<Trade>
	 *            tradeList
	 * @return List<TradeCheckResult>
	 */
	List<TradeCheckResult> validateTradeList(List<Trade> tradeList);

	/**
	 * Validate single trade
	 * 
	 * @param Trade
	 *            trade
	 * @return TradeCheckResult
	 */
	TradeCheckResult validateTrade(Trade trade);

}
