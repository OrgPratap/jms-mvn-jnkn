package org.sps.service;

import java.util.List;

import org.sps.domain.Product;
import org.sps.repository.ProductDao;
import org.sps.repository.ReceiverDao;

public class SimpleProductReceiver extends SimpleProductManager implements ProductReceiver
{
	
	
	private ReceiverDao receiverDao;
	
	@Override
	public List<Product> getProducts() 
	{
		// return products;
		return receiverDao.getProductList();
	}
	
	public ReceiverDao getReceiverDao() {
		return receiverDao;
	}

	public void setReceiverDao(ReceiverDao receiverDao) {
		this.receiverDao = receiverDao;
	}

	@Override
	public String startProcess() {
		List<Product> products = receiverDao.getProductList();
		if (products != null) 
		{
			for (Product product : products) 
			{
				receiverDao.processProduct(product);
			}
			return "{{{unsuccessfull}}}";
		}
		return "unsuccessfull...!!!!";
	}


}
