package com.wiivv.dto;

import java.util.Map;

public class CurrencyResponse {

	private boolean success;
	private Map<String, Float> quotes;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, Float> getQuotes() {
		return quotes;
	}

	public void setQuotes(Map<String, Float> quotes) {
		this.quotes = quotes;
	}

		
}
