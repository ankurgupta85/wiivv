package com.wiivv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wiivv.dto.CurrencyResponse;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Value("${currency.api_key}")
	private String currencyKey;

	@Value("${currency.url}")
	private String currencyUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public CurrencyResponse getCurrencyResponse() {
		String url = currencyUrl + currencyKey;
		ResponseEntity<CurrencyResponse> response = restTemplate.getForEntity(url, CurrencyResponse.class);
		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		}

		return null;

	}

}
