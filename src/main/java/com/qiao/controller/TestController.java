package com.qiao.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiao.domain.Student;
import com.qiao.service.TestService;
import com.yougou.ordercenter.api.order.IOrderForMerchantApi;
import com.yougou.ordercenter.vo.merchant.input.MerchantOrderOutStoreVo;
import com.yougou.pc.api.ICommodityClientApiService;
import com.yougou.pc.api.ICommodityMerchantApiService;
import com.yougou.pc.model.commodityinfo.CommodityDto;
import com.yougou.pc.model.commodityinfo.CommoditySearch;
import com.yougou.pc.model.product.Product;
import com.yougou.wfx.commodity.api.front.ICommodityStyleFrontApi;
import com.yougou.wfx.commodity.dto.input.CheckProductNumberInputDto;
import com.yougou.wfx.commodity.dto.output.CheckProductNumberOutputDto;
import com.yougou.wfx.commodity.dto.output.CommoditySimplifyStyleOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.commodity.dto.output.SellerCommodityCatRelaOutpuDto;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.finance.api.front.ICommissionPercentFrontApi;
import com.yougou.wfx.finance.dto.output.CommissionPercentOutputDto;
import com.yougou.wfx.member.api.front.IMemberAccountFrontApi;
import com.yougou.wfx.member.dto.input.MemberForWXInputDto;
import com.yougou.wfx.member.dto.output.MemberAccountOutputDto;
import com.yougou.wfx.order.api.front.IOrderFrontApi;
import com.yougou.wfx.order.dto.output.OrderCreateDto;
import com.yougou.wfx.order.dto.output.OrderDetailCreateDto;
import com.yougou.wfx.order.dto.output.OrderInfoDto;
import com.yougou.wfx.order.dto.output.OrderOutputDto;
import com.yougou.wfx.order.dto.output.OrderResultDto;
import com.yougou.wfx.outside.api.ICommodityForOutSideApi;
import com.yougou.wfx.outside.api.ILogisticsForOutSideApi;
import com.yougou.wfx.outside.api.IOrderForOutSideApi;
import com.yougou.wfx.outside.request.CommodityDetailGetRequest;
import com.yougou.wfx.outside.request.CommodityGetRequest;
import com.yougou.wfx.outside.request.LogisticsSendRequest;
import com.yougou.wfx.outside.request.OrderGetRequest;
import com.yougou.wfx.outside.request.OrderSearchRequest;
import com.yougou.wfx.outside.request.SkuQuantityUpdateRequest;
import com.yougou.wfx.outside.response.CommodityDetailGetResponse;
import com.yougou.wfx.outside.response.CommodityGetResponse;
import com.yougou.wfx.outside.response.LogisticsSendResponse;
import com.yougou.wfx.outside.response.OrderGetResponse;
import com.yougou.wfx.outside.response.OrderSearchResponse;
import com.yougou.wfx.outside.response.SkuQuantityUpdateResponse;

/**
 * <p>Title: TestController</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年3月30日
 */
@Controller
@RequestMapping("/test")
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Resource
	private TestService testService;
	
	@Resource
	private ICommodityStyleFrontApi commodityStyleFrontApi;
	@Resource
	private IOrderForOutSideApi orderForOutSideApi;
	@Resource
	private ILogisticsForOutSideApi logisticsForOutSideApi;
	@Resource
	private ICommodityForOutSideApi commodityForOutSideApi;
	@Resource
	private IOrderFrontApi orderFrontApi;
	@Resource
	private ICommodityMerchantApiService commodityMerchantApiService;
	@Resource
	private ICommodityClientApiService commodityClientApiService;
	@Resource
	private IOrderForMerchantApi orderForMerchantApi;
	@Resource
    private IMemberAccountFrontApi memberAccountFrontApi;
	@Resource
    private ICommissionPercentFrontApi commissionPercentFrontApi;
	
	@ResponseBody
	@RequestMapping(value="/getProductByNo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getProductByNo(String no){
		return testService.getProductByNo(no);
	}
	
	@ResponseBody
	@RequestMapping(value="/getProductByThirdPartyCode",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProductByThirdPartyCode(String no){
		return testService.getProductByThirdPartyCode(no);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/updateSellerCommodityShelvesStatus",  produces = MediaType.APPLICATION_JSON_VALUE)
	public WFXResult<Map<String, List<String>>> updateSellerCommodityShelvesStatus(int status){
		List<String> ids = new ArrayList<String>();

//		ids.add("b3bee5889b4547b9bdc22d7a69b10f0b");
		ids.add("0252721fe3f4440ea56ee802aafc9f30");
		ids.add("03ae6bd6a9114e15947af9a2e5958810");
		ids.add("02e0f033d464411cb82fefa7977939d3");
		ids.add("02e0f033d464411cb82fefa7977939d3");
//		ids.add("7126ea240b8b11e6a1c3000c2914ef43");
//		
//		ids.add("77c1fce70b8b11e6a1c3000c2914ef43");
//		
//		ids.add("7cd7ad180b8b11e6a1c3000c2914ef43");
//		ids.add("7cd7b1370b8b11e6a1c3000c2914ef43");
//		ids.add("7cd7b24d0b8b11e6a1c3000c2914ef43");
//		ids.add("7cd7b3680b8b11e6a1c3000c2914ef43");
		return commodityStyleFrontApi.updateSellerCommodityShelvesStatus(ids, status);
	}
	
	@ResponseBody
	@RequestMapping(value="/addClassify",  produces = MediaType.APPLICATION_JSON_VALUE)
	public WFXResult<Boolean> addClassify(){
		String classifyName = "aa";
		String parentNodeId = "";
		String sellerId = "s0000002";
		return commodityStyleFrontApi.addClassify(classifyName, parentNodeId, sellerId);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateClassify",  produces = MediaType.APPLICATION_JSON_VALUE)
	public WFXResult<Boolean> updateClassify(){
		String classifyName = "男鞋1";
		String classifyId = "20160408983";
		return commodityStyleFrontApi.updateClassify(classifyName, classifyId);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteClassify",  produces = MediaType.APPLICATION_JSON_VALUE)
	public WFXResult<Boolean> deleteClassify(){
		String classifyId = "111111111";
		return commodityStyleFrontApi.deleteClassify(classifyId);
	}
	
	@ResponseBody
	@RequestMapping(value="/queryClassify",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SellerCommodityCatRelaOutpuDto> queryClassify(){
		String sellerId = "s20160413001";
		return commodityStyleFrontApi.queryClassify(sellerId);
	}
	
	@ResponseBody
	@RequestMapping(value="/queryShelvesStatusAndInventoryStatus",  produces = MediaType.APPLICATION_JSON_VALUE)
	public CommoditySimplifyStyleOutputDto queryShelvesStatusAndInventoryStatus(){
		String commodityId = "00003ce0bce24f158dea671329187602";
		String productId = "0000023f868711e483c4a77c76dae2e0";
		return commodityStyleFrontApi.queryShelvesStatusAndInventoryStatus(commodityId, productId);
	}
	
	@ResponseBody
	@RequestMapping(value="/checkProductNumber",  produces = MediaType.APPLICATION_JSON_VALUE)
	public CheckProductNumberOutputDto checkProductNumber(){
		List<CheckProductNumberInputDto>  dto = new ArrayList<CheckProductNumberInputDto>();
		CheckProductNumberInputDto c1 = new CheckProductNumberInputDto();
		c1.setOrderNum("11");
		c1.setProductId("0000023f868711e483c4a77c76dae2e0");
		dto.add(c1);
		return commodityStyleFrontApi.checkProductNumber(dto);
	}
	
	@ResponseBody
	@RequestMapping(value="/getCommodityListByIds",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommodityStyleOutputDto> getCommodityListByIds(){
		List<String> commodityIds = new ArrayList<String>();
		commodityIds.add("00003ce0bce24f158dea671329187602");
		commodityIds.add("0000484473b94499b6487b0f5ba1718e");
		boolean includeProduct = true;
		boolean includeStock = false;
		return commodityStyleFrontApi.getCommodityListByIds(commodityIds, includeProduct, includeStock);
	}
	
	@ResponseBody
	@RequestMapping(value="/queryOrderList",  produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderSearchResponse queryOrderList(String merchantCode, String startTime, String endTime, String status, int pageNo, int pageSize) throws Exception{
		OrderSearchRequest request = new OrderSearchRequest();
		request.setStartTime(startTime);
		request.setMerchantCode(merchantCode);
		request.setEndTime(endTime);
		request.setStatus(status);
		request.setPageNo(pageNo);
		request.setPageSize(pageSize);
		OrderSearchResponse response = orderForOutSideApi.queryOrderList(request);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/getOrder",  produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderGetResponse getOrder(String orderNo) throws Exception{
		OrderGetRequest request = new OrderGetRequest();
		request.setWfxOrderNo(orderNo);
		OrderGetResponse response = orderForOutSideApi.getOrder(request);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryCommodityList",  produces = MediaType.APPLICATION_JSON_VALUE)
	public CommodityGetResponse queryCommodityList() throws Exception{
		CommodityGetRequest request = new CommodityGetRequest();
		request.setUpdateStartTime("2016-04-21 14:02:32");
		request.setUpdateEndTime("2016-04-21 14:02:34");
		request.setPageNo(1);
		request.setPageSize(20);
		CommodityGetResponse response = commodityForOutSideApi.queryCommodityList(request);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryCommodityDetailList",  produces = MediaType.APPLICATION_JSON_VALUE)
	public CommodityDetailGetResponse queryCommodityDetailList(String commodityNo) throws Exception{
		CommodityDetailGetRequest request = new CommodityDetailGetRequest();
		String[] noArr = new String[1];
		noArr[0] = commodityNo;
		request.setNoArr(noArr);
		CommodityDetailGetResponse response = commodityForOutSideApi.queryCommodityDetailList(request);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateSkuQuantity",  produces = MediaType.APPLICATION_JSON_VALUE)
	public SkuQuantityUpdateResponse updateSkuQuantity() throws Exception{
		SkuQuantityUpdateRequest request = new SkuQuantityUpdateRequest();
		request.setCommodityNo("100075951");
		request.setOuterSkuId("NJDB5153blue36");
		request.setQuantity(21);
		request.setType(2);
		SkuQuantityUpdateResponse response = commodityForOutSideApi.updateSkuQuantity(request);
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/logisticsSend",  produces = MediaType.APPLICATION_JSON_VALUE)
	public LogisticsSendResponse logisticsSend(String expressCode, String expressName, String expressNo, 
			String returnAddressNo, String orderInfos, String wfxOrderNo) throws Exception{
		LogisticsSendRequest request = new LogisticsSendRequest();
		List<String> orderInfoList = new ArrayList<String>();
		Collections.addAll(orderInfoList, orderInfos.split(";"));
		request.setExpressCode(expressCode);
		request.setExpressName(expressName);
		request.setExpressNo(expressNo);
		request.setWfxOrderNo(wfxOrderNo);
		request.setOrderInfoList(orderInfoList);
		request.setReturnAddressNo(returnAddressNo);
		LogisticsSendResponse response = logisticsForOutSideApi.logisticsSend(request);
		return response;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/1",  produces = MediaType.APPLICATION_JSON_VALUE)
	public CommodityDto test(){
		Date date1 = null;
		Date date2 = null;		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		try  
		{  
		    date1 = sdf.parse("2012-12-06");  
		    date2 = sdf.parse("2016-12-06");  
		}  
		catch (ParseException e)  
		{  
		    System.out.println(e.getMessage());  
		}  
		CommoditySearch search = new CommoditySearch();
		search.setMerchantCode("SP20150123257493");
		search.setIncludeProduct(true);
		search.setMinUpdateDateTime(date1);
		search.setMaxUpdateDateTime(date2);
		CommodityDto dto = commodityClientApiService.commoditySearchByModel(search);
		
		return dto;
	}
	
	@ResponseBody
	@RequestMapping(value="/out",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean orderOutStore(String orderSubNo, String levelCode, String expressCode, String logisticsCode, String logisticsName, int num){
		List<MerchantOrderOutStoreVo> merchantOrderOutStoreVoList = new ArrayList<MerchantOrderOutStoreVo>();
		String merchantCode = "SP20160523229447";
		if(StringUtils.isEmpty(orderSubNo)){
			orderSubNo = "C560707180012_1";
		}
		if(StringUtils.isEmpty(levelCode)){
			levelCode = "";
		}
		if(StringUtils.isEmpty(logisticsCode)){
			logisticsCode = "0001";
		}
		if(StringUtils.isEmpty(logisticsName)){
			logisticsName = "EMS";
		}
		if(StringUtils.isEmpty(expressCode)){
			expressCode = "232323";
		}
		Date outStoreDate = new Date();
		Boolean success = false;
		MerchantOrderOutStoreVo merchantOrderOutStoreVo = new MerchantOrderOutStoreVo();
		merchantOrderOutStoreVo.setMerchantCode(merchantCode);
        merchantOrderOutStoreVo.setOrderSubNo(orderSubNo);
        merchantOrderOutStoreVo.setLevelCode(levelCode);
        merchantOrderOutStoreVo.setCommodityNum(num);
        merchantOrderOutStoreVo.setLogisticsCode(logisticsCode);
        merchantOrderOutStoreVo.setExpressCode(expressCode);
		merchantOrderOutStoreVo.setDeliveryTime(outStoreDate);
		merchantOrderOutStoreVoList.add(merchantOrderOutStoreVo);
		
		success = orderForMerchantApi.outStoreForMerchantBatch(merchantOrderOutStoreVoList);
		return success;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryCommodity",  produces = MediaType.APPLICATION_JSON_VALUE)
	public CommodityDto queryCommodity(String commodityNo, int status, String minUpdateTime, String maxUpdateTime, int pageSize, int pageNo){
		String merchantCode = "SP20160523229447";
		CommodityDto result = null;
		Integer[] status1 = new Integer[]{status};
		result = commodityMerchantApiService.getCommodityByMerchantWithStatusTime(merchantCode, commodityNo, status1, minUpdateTime, maxUpdateTime, false, true, pageSize, pageNo);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/createOrder",  produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderResultDto createOrder(){
		OrderResultDto result = new OrderResultDto();
		OrderCreateDto orderCreateDto = new OrderCreateDto();
		orderCreateDto.setSellerId("201604260001");
		orderCreateDto.setSellerName("郑乔乔");
		orderCreateDto.setShopId("4590fe021224af2dsw011a11214522se");
		orderCreateDto.setBuyerId("128198398");
		orderCreateDto.setBuyerAccount("13722245551");
		orderCreateDto.setReceiverName("aljflll");
		orderCreateDto.setReceiverPhone("13233444124");
		orderCreateDto.setReceiverMobile("13233444124");
		orderCreateDto.setReceiverAddress("桃源南路38号");
		orderCreateDto.setReceiverState("广东省");
		orderCreateDto.setReceiverCity("深圳市");
		orderCreateDto.setReceiverDistrict("南山区");
		orderCreateDto.setReceiverZip("51800");
		orderCreateDto.setBuyerMessage("飞速发货");
		orderCreateDto.setNum(1);
		orderCreateDto.setPayment(0.01d);
		orderCreateDto.setPayType("wechatpay");
		orderCreateDto.setPostFee(0d);
		orderCreateDto.setSupplierId("GHS2016098767");
		orderCreateDto.setTotalFee(0.01d);
		List<OrderDetailCreateDto> detaillist = new ArrayList<OrderDetailCreateDto>();
		OrderDetailCreateDto detailDto1 = new OrderDetailCreateDto();
		detailDto1.setCommodityId("00005a2783ac4996bbe5692905538918");
		detailDto1.setProdId("7089ef4b42d811e3b38df1437eaeee9e");
		detailDto1.setProdName("圣大保罗 男士正品夏装双丝光棉纯色POLO宽松纯棉短袖T恤PS8KT201");
		detailDto1.setProdSpec("黑色，39码");
		detailDto1.setNum(1);
		detailDto1.setPrice(0.01d);
		detailDto1.setPayment(0.01d);
		detaillist.add(detailDto1);
		OrderDetailCreateDto detailDto2 = new OrderDetailCreateDto();
		detailDto2.setCommodityId("00008aaab7f7462f962019876e043b6b");
		detailDto2.setProdId("402736011d4011e483c4a77c76dae2e0");
		detailDto2.setProdName("莎诗特2015新款高跟凉鞋女防水台尖头Y字型镂空水钻时装细跟女鞋");
		detailDto2.setProdSpec("绿色，36码");
		detailDto2.setNum(1);
		detailDto2.setPrice(986d);
		detailDto2.setPayment(986d);
		detaillist.add(detailDto2);
		orderCreateDto.setOrderDetailCreateDto(detaillist);
		result = orderFrontApi.createOrder(orderCreateDto);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserOrderById",  produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderInfoDto getUserOrderById(String orderId){
		OrderOutputDto o1 = orderFrontApi.getCommiOrderById(orderId);
		OrderInfoDto o2 = orderFrontApi.getOrderById(orderId);
		logger.error("orderFrontApi.getCommiOrderById(orderId)   {}", o1);
		logger.error("orderFrontApi.getOrderById(orderId)   {}", o2);
		return o2;
	}
	
	@ResponseBody
	@RequestMapping(value="/getWeiXinMemberInfo",  produces = MediaType.APPLICATION_JSON_VALUE)
	public MemberAccountOutputDto getWeiXinMemberInfo(String unionId){
		MemberForWXInputDto input = new MemberForWXInputDto();
		input.setOpenId(unionId);
		MemberAccountOutputDto out = memberAccountFrontApi.wxMemberLogin(input, null);
		return out;
	}
	
	@ResponseBody
	@RequestMapping(value="/1111",  produces = MediaType.APPLICATION_JSON_VALUE)
	public CommissionPercentOutputDto ff(String brandNo, String commodityId){
		CommissionPercentOutputDto dto = commissionPercentFrontApi.getCommissionByCondition(brandNo, null, commodityId);
		return dto;
	}

}
