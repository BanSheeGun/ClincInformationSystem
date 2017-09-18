<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="entity.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
table, td, th {
	border:0px;
	border-collapse:collapse;
	padding: 5px 10px;
	font-size: 16px;
	font-family: Verdana;
	color: black;
	text-align:center;
}
th {
  background-color:black;
  color:white;
}
tr:nth-child(odd) {
background: grey;
  text-align:center;
}
tr:nth-child(even) {
background: #FFF;
  text-align:center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员</title>
</head>
<body>
<%
	PageModel pm = (PageModel)request.getAttribute("pm");
	int fn = pm.getFn();
	int ftn = pm.getFtn();
	List<Family> lf = pm.getLf();
%>
<h1 align="center" > <font size="10" face="微软雅黑">管理员信息页面 </font></h1> 
<h2><font size="6" face="微软雅黑">家庭信息表</font></h2>
<% if (ftn == 0) {%>
<font size="4" face="微软雅黑">暂无</font>
<%} else { %>
<table align="center">
</table>
<font size="4" face="微软雅黑">
	<% if (fn > 1) {%>
	<a href="${pageContext.request.contextPath}/adminServlet?fn=<%=fn-1 %>">上一页</a>
	<%}  
	for (int i = 1; i <= ftn; ++i) 
		if (i == fn) {%>
			<%=i %>
		<% } else {%>
			<a href="${pageContext.request.contextPath}/adminServlet?fn=<%=i %>"><%=i %></a>
	
	
	<%} if (fn < ftn) {%>
	<a href="${pageContext.request.contextPath}/adminServlet?fn=<%=fn+1 %>">下一页</a>
	<%} %>
</font>
<%} %>
</body>
</html>