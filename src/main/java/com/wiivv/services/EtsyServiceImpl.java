package com.wiivv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wiivv.dto.ProductListing;

@Service
public class EtsyServiceImpl implements EtsyService{

	@Value("${etsy.api.key}")
	private String etsyKey;

	@Value("${etsy.product.listing.url}")
	private String etsyListingUrl;

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ProductListing getProductListing(int limit, int offset) {
		String url = etsyListingUrl + etsyKey + "&limit=" + limit + "&offset=" + offset;
		ResponseEntity<ProductListing> response = restTemplate.getForEntity(url, ProductListing.class);
		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		}

		return null;
	}

	
}
