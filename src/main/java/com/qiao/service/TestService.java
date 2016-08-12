package com.qiao.service;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.qiao.domain.Student;
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
	private final Logger LOGGER = Logger.getLogger(TestService.class);
	@Resource
	private ICommodityBaseApiService commodityBaseApiService;
	@Resource
	private IOrderApi orderApi;
	
	public Product getProductByNo(@NotEmpty String productNo){
		Product product = commodityBaseApiService.getProductByNo(productNo);
		return product;
	}
	
	public List<Product> getProductByThirdPartyCode(String thirdPartyCode){
		List<Product> list = commodityBaseApiService.getProductByThirdPartyCode(thirdPartyCode, false);
		return list;
	}
	
	public void test(@NotEmpty.List(value={@NotEmpty(target="id"), @NotEmpty(target="name")})
			Student student){
		System.out.println("aaaaaaaaaaa");
		System.out.println("aaaaaaaaaaa");
		System.out.println("aaaaaaaaaaa");
		System.out.println("aaaaaaaaaaa");
		System.out.println("aaaaaaaaaaa");
	}
	
	public void test2(@NotBlank String s){
		System.out.println("aaaaaaaaaaa");
		System.out.println("aaaaaaaaaaa");
		System.out.println("aaaaaaaaaaa");
		System.out.println("aaaaaaaaaaa");
		System.out.println("aaaaaaaaaaa");
	}
		
}
