package org.sps.service;

import java.util.List;

import org.sps.domain.Product;
import org.sps.repository.ProductDao;

public class SimpleProductManager implements ProductManager
{

    private List<Product> products;
	protected ProductDao productDao;
	public List<Product> getProducts() 
	{
		// return products;
		return productDao.getProductList();
	}
	public void increasePrice(int percentage) 
	{
		List<Product> products = productDao.getProductList();
		if (products != null) 
		{
			for (Product product : products) 
			{
				double newPrice = product.getPrice().doubleValue() *(100 + percentage)/100;
				product.setPrice(newPrice);
				productDao.saveProduct(product);
			}
		}
	}
	public void setProductDao(ProductDao productDao) 
	{
		this.productDao = productDao;
	}
	 public void setProducts(List<Product> products) {
	 this.products = products;
	 }

}
