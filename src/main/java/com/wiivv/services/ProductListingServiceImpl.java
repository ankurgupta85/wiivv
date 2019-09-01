package com.wiivv.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wiivv.dto.CurrencySingleton;
import com.wiivv.dto.Product;
import com.wiivv.dto.ProductListing;
import com.wiivv.enums.CurrencyEnum;

@Service
public class ProductListingServiceImpl implements ProductListingService {

	@Autowired
	private EtsyService etsyService;

	@Autowired
	private CurrencySingleton currencySingleton;

	@Override
	public ResponseEntity<List<Product>> getProductListing(int limit, int offset, String currency) {
		try {
			if (StringUtils.isBlank(currency) || !EnumUtils.isValidEnumIgnoreCase(CurrencyEnum.class, currency)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			ProductListing listing = etsyService.getProductListing(limit, offset);
			Float exchangeRate = currencySingleton.getCurrencyFor(currency.toUpperCase());
			if (listing == null || org.springframework.util.CollectionUtils.isEmpty(listing.getResults())
					|| exchangeRate == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			List<Product> productList = new ArrayList<Product>();
			for (Product product : listing.getResults()) {
				String productPrice = product.getPrice();
				Float calculatedPrice = Float.valueOf(productPrice) * exchangeRate;
				product.setPrice(String.valueOf(calculatedPrice));
				productList.add(product);
			}
			
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
