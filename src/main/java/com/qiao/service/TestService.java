package com.qiao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yougou.ordercenter.api.order.IOrderApi;
import com.yougou.pc.api.ICommodityBaseApiService;
import com.yougou.pc.model.product.Product;

/**
 * <p>Title: TestService</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月30日
 */
@Service
public class TestService {
	@Resource
	private ICommodityBaseApiService commodityBaseApiService;
	@Resource
	private IOrderApi orderApi;
	
	public Product getProductByNo(String productNo){
		Product product = commodityBaseApiService.getProductByNo(productNo);
		return product;
	}
	
	public List<Product> getProductByThirdPartyCode(String thirdPartyCode){
		List<Product> list = commodityBaseApiService.getProductByThirdPartyCode(thirdPartyCode, false);
		return list;
	}
}
