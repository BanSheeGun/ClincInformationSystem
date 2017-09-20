<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="entity.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
table, td, th {
	border:1px;
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
	FamilyPageModel fpm = (FamilyPageModel)request.getAttribute("fpm");
	int fn = fpm.getFn();
	int ftn = fpm.getFtn();
	List<Family> lf = fpm.getLf();
	PatientPageModel ppm = (PatientPageModel)request.getAttribute("ppm");
	int pn = ppm.getPn();
	int ptn = ppm.getPtn();
	List<Patient> lp = ppm.getLp();
	DentistPageModel dpm = (DentistPageModel)request.getAttribute("dpm");
	int dn = dpm.getDn();
	int dtn = dpm.getDtn();
	List<Dentist> ld = dpm.getLd();
%>
<h1 align="center" > <font size="10" face="微软雅黑">管理员信息页面 </font></h1> 
<h2 align="center"><font size="6" face="微软雅黑">家庭信息表</font></h2>
<% if (ftn == 0) {%>
<p align="center"><font size="4" face="微软雅黑">暂无</font></p>
<%} else { %>
<table align="center" width="80%">
<tr>
	<th>家庭编号</th>
	<th>家庭地址</th>
	<th>操作</th>
</tr>
<%
	for (Family i : lf) {
%>
	<tr>
		<td><%=i.getFamilyId() %></td>
		<td><%=i.getAddress() %></td>
		<td>
			<a href="${pageContext.request.contextPath}/familyServlet?op=view&fid=<%=i.getFamilyId() %>">详细</a>
			<a href="${pageContext.request.contextPath}/familyServlet?op=delete&fid=<%=i.getFamilyId() %>">删除</a>
		</td>
	</tr>
<%} %>
</table>
<div align="center">
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
</div>
<%} %>

<h2 align="center"><font size="6" face="微软雅黑">病人信息表</font></h2>
<% if (ptn == 0) {%>
<p align="center"><font size="4" face="微软雅黑">暂无</font></p>
<%} else { %>
<table align="center" width="80%">
<tr>
	<th>患者编号</th>
	<th>家庭编号</th>
	<th>姓名</th>
	<th>操作</th>
</tr>
<%
	for (Patient i : lp) {
%>
	<tr>
		<td><%=i.getPatientId() %></td>
		<td><%=i.getFamilyId() %></td>
		<td><%=i.getName() %></td>
		<td>
			<a href="${pageContext.request.contextPath}/patientServlet?op=view&pid=<%=i.getPatientId() %>">详细</a>
			<a href="${pageContext.request.contextPath}/patientServlet?op=delete&pid=<%=i.getPatientId() %>">删除</a>
		</td>
	</tr>
<%} %>
</table>
<div align="center">
<font size="4" face="微软雅黑">
	<% if (pn > 1) {%>
	<a href="${pageContext.request.contextPath}/adminServlet?pn=<%=pn-1 %>">上一页</a>
	<%}  
	for (int i = 1; i <= ptn; ++i) 
		if (i == pn) {%>
			<%=i %>
		<% } else {%>
			<a href="${pageContext.request.contextPath}/adminServlet?pn=<%=i %>"><%=i %></a>
	
	
	<%} if (pn < ptn) {%>
	<a href="${pageContext.request.contextPath}/adminServlet?pn=<%=pn+1 %>">下一页</a>
	<%} %>
</font>
</div>
<%} %>

<h2 align="center"><font size="6" face="微软雅黑">牙医信息表</font></h2>
<% if (dtn == 0) {%>
<p align="center"><font size="4" face="微软雅黑">暂无</font></p>
<%} else { %>
<table align="center" width="80%">
<tr>
	<th>牙医编号</th>
	<th>诊所编号</th>
	<th>姓名</th>
	<th>操作</th>
</tr>
<%
	for (Dentist i : ld) {
%>
	<tr>
		<td><%=i.getDentistId() %></td>
		<td><%=i.getClinicId() %></td>
		<td><%=i.getName() %></td>
		<td>
			<a href="${pageContext.request.contextPath}/dentistServlet?op=view&did=<%=i.getDentistId() %>">详细</a>
			<a href="${pageContext.request.contextPath}/dentistServlet?op=delete&did=<%=i.getDentistId() %>">删除</a>
		</td>
	</tr>
<%} %>
</table>
<div align="center">
<font size="4" face="微软雅黑">
	<% if (dn > 1) {%>
	<a href="${pageContext.request.contextPath}/adminServlet?dn=<%=dn-1 %>">上一页</a>
	<%}  
	for (int i = 1; i <= dtn; ++i) 
		if (i == dn) {%>
			<%=i %>
		<% } else {%>
			<a href="${pageContext.request.contextPath}/adminServlet?dn=<%=i %>"><%=i %></a>
	
	
	<%} if (dn < dtn) {%>
	<a href="${pageContext.request.contextPath}/adminServlet?dn=<%=dn+1 %>">下一页</a>
	<%} %>
</font>
</div>
<%} %>

</body>
</html>