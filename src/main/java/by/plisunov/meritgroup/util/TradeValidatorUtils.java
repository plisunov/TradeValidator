package by.plisunov.meritgroup.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Component with trade-checks logic.
 * 
 * @author Andrey
 *
 */
@Component
public class TradeValidatorUtils {

	@Value("${supported.customers}")
	private String supportedCustomers;

	@Value("${legal.entity}")
	private String legalEntitySetting;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * Check that the date1 before date2
	 * 
	 * @param Optional<String>
	 *            date1 (yyyy-mm-dd format)
	 * @param Optional<String>
	 *            date2 (yyyy-mm-dd format)
	 * @return boolean(true/false)
	 */
	public boolean checkDateBeforeDate(Optional<String> date1, Optional<String> date2) {
		if (toDate(date1.get().trim()).isBefore(toDate(date2.get().trim()))) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param Optional<String>
	 *            legalEntity
	 * @return boolean(true/false)
	 */
	public boolean checkLegalEntity(Optional<String> legalEntity) {
		return legalEntitySetting.equals(legalEntity.get().trim().toUpperCase());
	}

	/**
	 * Check that the date fall on weekends days
	 * 
	 * @param Optional<String>
	 *            dateStr (yyyy-mm-dd format)
	 * @return boolean(true/false)
	 */
	public boolean checkWeekend(Optional<String> dateStr) {
		if (DayOfWeek.SATURDAY.equals(getDayOfWeek(dateStr.get()))
				|| (DayOfWeek.SUNDAY.equals(getDayOfWeek(dateStr.get())))) {
			return true;
		}
		return false;
	}

	/**
	 * Check that 2 currencys codes from pair are correct
	 * 
	 * @param Optional<String>
	 *            currencyPair
	 * @return boolean(true/false)
	 */
	public boolean checkCurrency(Optional<String> currencyPair) {
		if (currencyPair.get().trim().length() != 6) {
			return false;
		}
		String currency1 = currencyPair.get().trim().substring(0, 3).toUpperCase();
		String currency2 = currencyPair.get().trim().substring(3).toUpperCase();
		try {
			Currency.getInstance(currency1);
			Currency.getInstance(currency2);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	/**
	 * Check that trade style is legal
	 * 
	 * @param Optional<String>
	 *            style
	 * @return boolean(true/false)
	 */
	public boolean checkStyle(Optional<String> style) {
		if ((!TradeValidatorConstants.AMERICAN_STYLE.equals(style.get().trim().toUpperCase()))
				&& (!TradeValidatorConstants.EUROPEAN_STYLE.equals(style.get().trim().toUpperCase()))) {
			return false;
		}
		return true;
	}

	/**
	 * Check that customer is supported
	 * 
	 * @param Optional<String>
	 *            customer
	 * @return boolean(true/false)
	 */
	public boolean checkCustomer(Optional<String> customer) {
		if (supportedCustomers.indexOf(customer.get().trim().toUpperCase()) == -1) {
			return false;
		}
		return true;
	}

	/**
	 * Parse date from String
	 * 
	 * @param Optional<String>
	 *            dateStr (yyyy-mm-dd format)
	 * @return LocalDate date
	 */
	private LocalDate toDate(String dateStr) {
		return LocalDate.parse(dateStr, formatter);
	}

	/**
	 * Parse date from String and return DayOfWeek
	 * 
	 * @param Optional<String>
	 *            dateStr (yyyy-mm-dd format)
	 * @return DayOfWeek
	 */
	private DayOfWeek getDayOfWeek(String dateStr) {
		return LocalDate.parse(dateStr).getDayOfWeek();
	}

}
