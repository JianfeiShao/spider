package com.zht.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	Map<String, String> params = new HashMap<String, String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Enumeration<String> names = filterConfig.getInitParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			params.put(name, filterConfig.getInitParameter(name));
		}
		System.out.println("EncodeFilter init");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encodeCoding = params.get("encoding");
		request.setCharacterEncoding(encodeCoding);
		response.setCharacterEncoding(encodeCoding);
		chain.doFilter(request, response);
		System.out.println("EncodeFilter doFilter");

	}

	@Override
	public void destroy() {
		System.out.println("EncodeFilter destroy");
	}

}
