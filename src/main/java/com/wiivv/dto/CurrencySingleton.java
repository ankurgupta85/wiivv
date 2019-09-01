package com.wiivv.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wiivv.enums.CurrencyEnum;
import com.wiivv.services.CurrencyService;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CurrencySingleton {

	private Map<String, Float> currencyMap = new HashMap<String, Float>();

	private Date lastFetchDateTime = new Date(0L);

	@Autowired
	private CurrencyService currencyService;

	public Float getCurrencyFor(String currency) {
		Date currentTime = new Date();
		if (lastFetchDateTime == null || !DateUtils.isSameDay(currentTime, lastFetchDateTime)) {
			// Fetch the latest currency rates.
			// Set it to currencyMap
			CurrencyResponse currencyResponse = currencyService.getCurrencyResponse();
			if (currencyResponse != null) {
				currencyMap.clear();
				for (CurrencyEnum currencyEnum : CurrencyEnum.values()) {
					String key = "USD" + currencyEnum.name();
					currencyMap.put(currencyEnum.name().toUpperCase(), currencyResponse.getQuotes().get(key));

				}

				lastFetchDateTime = currentTime;
			}

		}

		// Fetch value from currency map
		// return the exchange rate
		return currencyMap.get(currency);
	}

}
