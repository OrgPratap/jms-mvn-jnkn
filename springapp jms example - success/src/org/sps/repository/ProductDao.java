package org.sps.repository;

import java.util.List;

import org.sps.domain.Product;

public interface ProductDao 
{
	public static final String RECEIVER_QUEUE_NAME = "receiver_queue_r1";
	public List<Product> getProductList();
	public void saveProduct(Product prod);
}
