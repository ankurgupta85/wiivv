package com.wiivv.services;

import org.springframework.stereotype.Service;

import com.wiivv.dto.CurrencyResponse;

@Service
public interface CurrencyService {
	
	public CurrencyResponse getCurrencyResponse();
	

}
