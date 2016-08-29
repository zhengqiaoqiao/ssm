package com.qiao.system.log;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qiao.system.annotation.UserLogger;
import com.qiao.system.bean.UserLoggerBean;
import com.qiao.util.CommonUtil;
import com.qiao.util.DateUtil;
import com.qiao.util.JsonUtil;


/**
 * 使用AOP对方法进行日志记录
 */
@Aspect
@Component
public class UserLoggerIntercepter {
	private static final Logger logger = LoggerFactory.getLogger(UserLoggerIntercepter.class);
	@Autowired  
	HttpServletRequest request; //这里可以获取到request
	//切点    
    @Pointcut("@annotation(com.qiao.system.annotation.UserLogger)")    
    public void pointcut() {}    
    
    @Before(value="pointcut()") 
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    	
    }
    
    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	Date start = null;
    	Date end = null;
    	Method method = this.getMethod(joinPoint);
    	UserLogger annotation = method.getAnnotation(UserLogger.class);
    	UserLoggerBean loggerBean = new UserLoggerBean();
    	if (logger.isInfoEnabled()) {
    		try{
    			start = new Date();
        		//请求的IP    
                String ip = CommonUtil.getRemoteHost(request);
                String methodName = joinPoint.getSignature().getName();
        		String className = joinPoint.getTarget().getClass().getName();
        		Object[] args = joinPoint.getArgs();
        		loggerBean.setMethodName(className+"."+methodName);
                loggerBean.setRequestIp(ip);
                loggerBean.setRequestStartTime(DateUtil.format(start));
                loggerBean.setMethodDesc(annotation.methodNote());
                Map<String, Object> params = new HashMap<String, Object>();
                if (args != null && args.length > 0) {
        			for (int i = 0; i < args.length; i++) {
        				params.put("arg-" + i, args[i]);
        			}
        		}
                loggerBean.setParams(params);
        	}catch(Exception e){
        		logger.error("设置方法请求内容日志功能发生异常：", e);
        	}
    	}

    	Object result = null;
    	Exception error = null;
    	try{
    		result = joinPoint.proceed();
    	}catch(Exception e){
    		error = e;
    		logger.error("调用方法执行异常：", e);
    	}
    	if (logger.isInfoEnabled()) {
    		try{
    			end = new Date();
    			loggerBean.setExecutionTime(end.getTime()-start.getTime());
    			loggerBean.setRequestEndTime(DateUtil.format(end));
    			if(annotation.isPrintResult()){
    				loggerBean.setResult(result);
    			}
                logger.info("系统日志：{}", JsonUtil.serialize(loggerBean));
        	}catch(Exception e){
        		logger.error("设置方法请求的结果日志功能发生异常：", e);
        	}
    	}

    	if (error != null) {
			throw error;
		}
        return result;
    }
    
    @After("pointcut()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
    	
    }
    
    /**
	 * @param joinPoint
	 * @return
	 * @throws NoSuchMethodException
	 */
	protected Method getMethod(final JoinPoint joinPoint)
			throws NoSuchMethodException {
		final Signature sig = joinPoint.getSignature();
		if (!(sig instanceof MethodSignature)) {
			throw new NoSuchMethodException(
					"This annotation is only valid on a method.");
		}
		final MethodSignature msig = (MethodSignature) sig;
		final Object target = joinPoint.getTarget();
		return target.getClass().getMethod(msig.getName(),
				msig.getParameterTypes());
	}
}
