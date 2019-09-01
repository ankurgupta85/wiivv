package com.wiivv.services;

import org.springframework.stereotype.Service;

import com.wiivv.dto.ProductListing;

@Service
public interface EtsyService {

	public ProductListing getProductListing(int limit, int offset);
}
