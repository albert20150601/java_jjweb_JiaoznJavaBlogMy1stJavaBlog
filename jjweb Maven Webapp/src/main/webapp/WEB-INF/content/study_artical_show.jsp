<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<title>Hello,world</title>
  </head>
  <body>
   <jsp:include page="main_head.jsp"></jsp:include>
   
study_c_showArtical.jsp
题目：<s:property value="artical.title"></s:property><br>
作者：<s:property value="artical.author"></s:property><br>
日期：<s:property value="artical.time"></s:property><br>
<hr>
正文：<s:property value="artical.content" escape="false"></s:property><br>
阅读次数：<s:property value="artical.access"/>
  <s:debug></s:debug>
   <jsp:include page="main_foot.jsp"></jsp:include>

  </body>
</html>
