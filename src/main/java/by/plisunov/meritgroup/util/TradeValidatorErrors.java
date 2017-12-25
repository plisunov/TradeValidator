package by.plisunov.meritgroup.util;

/**
 * List of patterns for error messages
 * 
 * @author Andrey
 *
 */
public class TradeValidatorErrors {
	/**
	 * Value date is before trade date
	 * 
	 * @argument value date
	 * @argument trade date
	 */
	public static final String VALUEDATE_BEFORE_TRADEDATE = "Value date <b>{0}</b> is before trade date <b>{1}</b>. <br>";
	/**
	 * Value date fall on weekend
	 * 
	 * @argument value date
	 */
	public static final String VALUEDATE_FALL_ON_WEEKEND = "Value date <b>{0}</b> fall on weekend. <br>";
	/**
	 * Customer is not supported error
	 * 
	 * @argument customer
	 */
	public static final String UNSUPPORTED_CUSTOMER = "Customer <b>{0}</b> is not supported. <br>";
	/**
	 * Invalid currency error
	 * 
	 * @argument currency pair
	 */
	public static final String INVALID_CURRENCY = "Invalid currency <b>{0}</b>. <br>";
	/**
	 * Unsupported style error
	 * 
	 * @argument style
	 */
	public static final String UNSUPPORTED_STYLE = "Unsupported style <b>{0}</b>. <br>";
	/**
	 * Excercise start date is before trade date
	 * 
	 * @argument excercise date
	 * @argument trade date
	 */
	public static final String EXCERCISE_DATE_BEFORE_TRADE_DATE = "Excercise start date <b>{0}</b> is before trade date <b>{1}</b>. <br>";
	/**
	 * Excercise start date is after expire date
	 * 
	 * @argument excercise date
	 * @argument expire date
	 */
	public static final String EXCERCISE_DATE_AFTER_EXPIRE_DATE = "Excercise start date <b>{0}</b> is after expire date <b>{1}</b>. <br>";
	/**
	 * Expire date is after delivery date
	 * 
	 * @argument expire date
	 * @argument delivery date
	 */
	public static final String EXPIRE_DATE_AFTER_DELIVERY_DATE = "Expire date <b>{0}</b> is after delivery date <b>{1}</b>. <br>";
	/**
	 * Premium date is after delivery date
	 * 
	 * @argument premium date
	 * @argument delivery date
	 */
	public static final String PREMIUM_DATE_AFTER_DELIVERY_DATE = "Premium date <b>{0}</b> is after delivery date <b>{1}</b>. <br>";
	/**
	 * Field not found or null exception
	 * 
	 * @argument field name
	 */
	public static final String MISSED_FIELD = "Field <b>{0}</b> not found or empty. <br>";
	/**
	 * Legal entity is not supported error
	 * 
	 * @argument legal entity
	 */
	public static final String UNSUPPORTED_LEGAL_ENTITY = "Legal entity <b>{0}</b> is not supported. <br>";
}
