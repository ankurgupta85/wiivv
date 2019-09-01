package com.wiivv.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wiivv.dto.Product;

@Service
public interface ProductListingService {

	public ResponseEntity<List<Product>> getProductListing(int limit, int offset, String currency);
}
