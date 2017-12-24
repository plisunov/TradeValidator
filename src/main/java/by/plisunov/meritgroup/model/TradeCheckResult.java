package by.plisunov.meritgroup.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Object for save information with results of checking Trade.
 * 
 * @field Optional<String> trade (Trade.toString())
 * @field Optional<String> status (OK, FAIL)
 * @field Optional<List<String>> errors (List with errors)
 * @author Andrey
 *
 */
public class TradeCheckResult {

	private Optional<String> trade;

	private Optional<String> status;

	private Optional<List<String>> errors;

	public TradeCheckResult() {
		this.setErrors(new ArrayList<>());
	}

	public Optional<String> getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = Optional.of(trade);
	}

	public Optional<String> getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = Optional.of(status);
	}

	public Optional<List<String>> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = Optional.ofNullable(errors);
	}

}
