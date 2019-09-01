package com.wiivv.controller;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wiivv.dto.Product;
import com.wiivv.enums.CurrencyEnum;
import com.wiivv.services.ProductListingService;

@RestController
@RequestMapping(value = "/v1/products")
public class ProductListingController {

	@Autowired
	private ProductListingService listingService; 
	
	@GetMapping("/listing")
	public ResponseEntity<List<Product>> getProductListing(
			@RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
			@RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
			@RequestParam(name = "currency", required = true) String currency) {
		if (StringUtils.isBlank(currency) || !EnumUtils.isValidEnumIgnoreCase(CurrencyEnum.class, currency)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return listingService.getProductListing(limit, offset, currency);
	}

}
