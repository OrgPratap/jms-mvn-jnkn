package org.sps.service;

import java.io.Serializable;
import java.util.List;

import org.sps.domain.Product;

public interface ProductManager extends Serializable 
{
	public void increasePrice(int percentage);
	public List<Product> getProducts();
}
