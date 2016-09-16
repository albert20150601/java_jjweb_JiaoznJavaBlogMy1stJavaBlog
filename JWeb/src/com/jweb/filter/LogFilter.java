package com.jweb.filter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.jweb.log.LogWriter;


public class LogFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
//	private FilterConfig config; 
//    // ʵ�ֳ�ʼ������ 
//    public void init(FilterConfig config) { 
//        this.config = config; 
//    } 
//    // ʵ�����ٷ��� 
//    public void destroy() { 
//        this.config = null; 
//    } 
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		 // ��ȡServletContext �������ڼ�¼��־ 
//        ServletContext context = this.config.getServletContext(); 
        // ������ת����HttpServletRequest ���� 
        HttpServletRequest hrequest = (HttpServletRequest) request; 
        // ��¼��־ 
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        LogWriter.method1("\\log.txt", time+":����- "+hrequest.getRemoteAddr()+" ����- "+hrequest.getRequestURI());
//        context.log("Filter�Ѿ��ػ��û�������ĵ�ַ: " + hrequest.getServletPath()); 
        try { 
            // Filter ֻ����ʽ����������Ȼת����Ŀ�ĵ�ַ�� 
            chain.doFilter(request, response); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
