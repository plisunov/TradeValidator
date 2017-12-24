package by.plisunov.meritgroup.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Trade object contains all fields from JSON and additional Map<String, Object>
 * for extra field. All fields are <b>Optional</b> and can take <b>null</b>
 * value. Add NPE check in your code.
 * 
 * @author Andrey
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Trade {

	@JsonProperty("customer")
	private Optional<String> customer;

	@JsonProperty("ccyPair")
	private Optional<String> ccyPair;

	@JsonProperty("type")
	private Optional<String> type;

	@JsonProperty("direction")
	private Optional<String> direction;

	@JsonProperty("tradeDate")
	private Optional<String> tradeDate;

	@JsonProperty("amount1")
	private Optional<Double> amount1;

	@JsonProperty("amount2")
	private Optional<Double> amount2;

	@JsonProperty("rate")
	private Optional<Double> rate;

	@JsonProperty("valueDate")
	private Optional<String> valueDate;

	@JsonProperty("legalEntity")
	private Optional<String> legalEntity;

	@JsonProperty("trader")
	private Optional<String> trader;

	@JsonProperty("style")
	private Optional<String> style;

	@JsonProperty("strategy")
	private Optional<String> strategy;

	@JsonProperty("deliveryDate")
	private Optional<String> deliveryDate;

	@JsonProperty("expiryDate")
	private Optional<String> expiryDate;

	@JsonProperty("payCcy")
	private Optional<String> payCcy;

	@JsonProperty("premium")
	private Optional<Double> premium;

	@JsonProperty("premiumCcy")
	private Optional<String> premiumCcy;

	@JsonProperty("premiumType")
	private Optional<String> premiumType;

	@JsonProperty("premiumDate")
	private Optional<String> premiumDate;

	@JsonProperty("excerciseStartDate")
	private Optional<String> excerciseStartDate;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Optional<String> getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = Optional.ofNullable(customer);
	}

	public Optional<String> getCcyPair() {
		return ccyPair;
	}

	public void setCcyPair(String ccyPair) {
		this.ccyPair = Optional.ofNullable(ccyPair);
	}

	public Optional<String> getType() {
		return type;
	}

	public void setType(String type) {
		this.type = Optional.ofNullable(type);
	}

	public Optional<String> getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = Optional.ofNullable(direction);
	}

	public Optional<String> getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = Optional.ofNullable(tradeDate);
	}

	public Optional<Double> getAmount1() {
		return amount1;
	}

	public void setAmount1(Double amount1) {
		this.amount1 = Optional.ofNullable(amount1);
	}

	public Optional<Double> getAmount2() {
		return amount2;
	}

	public void setAmount2(Double amount2) {
		this.amount2 = Optional.ofNullable(amount2);
	}

	public Optional<Double> getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = Optional.ofNullable(rate);
	}

	public Optional<String> getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = Optional.ofNullable(valueDate);
	}

	public Optional<String> getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(String legalEntity) {
		this.legalEntity = Optional.ofNullable(legalEntity);
	}

	public Optional<String> getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = Optional.ofNullable(trader);
	}

	public Optional<String> getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = Optional.ofNullable(style);
	}

	public Optional<String> getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = Optional.ofNullable(strategy);
	}

	public Optional<String> getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = Optional.ofNullable(deliveryDate);
	}

	public Optional<String> getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = Optional.ofNullable(expiryDate);
	}

	public Optional<String> getPayCcy() {
		return payCcy;
	}

	public void setPayCcy(String payCcy) {
		this.payCcy = Optional.ofNullable(payCcy);
	}

	public Optional<Double> getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
		this.premium = Optional.ofNullable(premium);
	}

	public Optional<String> getPremiumCcy() {
		return premiumCcy;
	}

	public void setPremiumCcy(String premiumCcy) {
		this.premiumCcy = Optional.ofNullable(premiumCcy);
	}

	public Optional<String> getPremiumType() {
		return premiumType;
	}

	public void setPremiumType(String premiumType) {
		this.premiumType = Optional.ofNullable(premiumType);
	}

	public Optional<String> getPremiumDate() {
		return premiumDate;
	}

	public void setPremiumDate(String premiumDate) {
		this.premiumDate = Optional.ofNullable(premiumDate);
	}

	public Optional<String> getExcerciseStartDate() {
		return excerciseStartDate;
	}

	public void setExcerciseStartDate(String excerciseStartDate) {
		this.excerciseStartDate = Optional.ofNullable(excerciseStartDate);
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "Customer: " + this.customer.get() + ", trade date " + this.tradeDate.get() + ", type "
				+ this.type.get();
	}

}
