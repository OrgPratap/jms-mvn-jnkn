package org.sps.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.sps.domain.Product;
import org.sps.service.ProductManager;
import org.sps.service.ProductReceiver;

public class ProductReceiverFormController implements Controller
{
	
	protected final Log logger=LogFactory.getLog(getClass());
	private ProductManager productManager;
	private ProductReceiver productReceiver;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		String now = (new java.util.Date()).toString();
		logger.info("returning hello view with " + now);
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("now", now);
		myModel.put("products", this.productManager.getProducts());
		productReceiver.startProcess();
		return new ModelAndView("successpage", "model", myModel);
	}
	public void setProductManager(ProductManager productManager) 
	{
		this.productManager = productManager;
	}
	public void setProductReceiver(ProductReceiver productReceiver) {
		this.productReceiver = productReceiver;
	}
	
}
