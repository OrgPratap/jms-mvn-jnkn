package org.sps.repository;

import java.util.List;

import org.sps.domain.Product;

public class InMemoryProductDao implements ProductDao 
{
	private List<Product> productList;
	public InMemoryProductDao(List<Product> productList)
	{
		this.productList = productList;
	}
	public List<Product> getProductList()
	{
		return productList;
	}
	public void saveProduct(Product prod)
	{
	}
}
