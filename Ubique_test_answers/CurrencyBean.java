package com.example.demo.vo;

public class CurrencyBean {
	private String currency;
	private Integer amount;
	
	
	public CurrencyBean() {
	}
	
	public CurrencyBean(String currency, Integer amount) {
		super();
		this.currency = currency;
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
