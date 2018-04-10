package com.kxjl.brain.police.api.web;

import com.kxjl.brain.police.service.api.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域支持
 * @Author DengWei
 * @Date 2017/5/12 16:07
 */
@Component
public class SimpleCORSFilter implements Filter {

	 @Autowired
	 RedisService redisService;

	 public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	       	HttpServletRequest request = (HttpServletRequest)req;
		    HttpServletResponse response = (HttpServletResponse) res;
//		    String token =  request.getParameter("token");
//		    System.out.println(token);
//		    if(StringUtil.isNullOrBlank(token)){
//			   response.getWriter().append("{\"msg\":\"验证信息失败\"}").flush();
//			   return;
//		    }
//		    String userInfo = redisService.get(Constants.LOGIN+token);
//		    if(StringUtil.isNullOrBlank(userInfo)){
//			    response.getWriter().append("{\"msg\":\"验证信息失败\"}").flush();
//			    return;
//		    }

		    response.setHeader("Access-Control-Allow-Origin", "*");
		    response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
		    response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, Connection, User-Agent, authorization, sw-useragent, sw-version");

		    //预处理  415 code  Just REPLY OK if request method is OPTIONS for CORS (pre-flight)
		    if ( request.getMethod().equals("OPTIONS") ) {
			     response.setStatus(HttpServletResponse.SC_OK);
				 return;
		    }

			String heard = request.getHeader("Accept-Encoding");

	        chain.doFilter(req, res);
	    }

	    public void init(FilterConfig filterConfig) {}

	    public void destroy() {}

}
