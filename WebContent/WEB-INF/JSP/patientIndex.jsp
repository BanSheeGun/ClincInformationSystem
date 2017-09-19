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
<title>病人信息</title>
</head>
<body>
<h1 align="center"><font size="8" face="微软雅黑"> 病人  </font></h1>
<%
	String op = null;
	try {
		op = (String)request.getAttribute("op");
	} catch (Exception e) {
		op = request.getParameter("op");
	}
	
	Patient p = (Patient)request.getAttribute("p");
	if (op.equals("view")) {
%>
<table align="center" width="80%">
<tr>
	<th>病人编号</th>
	<th>家庭编号</th>
	<th>姓名</th>
	<th>性别</th>
</tr>
<tr>
	<td><%=p.getPatientId() %></td>
	<td><%=p.getFamilyId() %></td>
	<td><%=p.getName() %></td>
	<td><%=p.getSex() %></td>
</tr>
<tr>
	<th>年龄</th>
	<th>邮箱</th>
	<th>电话</th>
	<th>住址</th>
</tr>
<tr>
	<td><%=p.getAge() %></td>
	<td><%=p.getEmail() %></td>
	<td><%=p.getTel() %></td>
	<td><%=p.getAddress() %></td>
</tr>
</table>
<p align="center">
<font size="4" face="微软雅黑"> 
<a href="${pageContext.request.contextPath}/patientServlet?op=edit&pid=<%=p.getPatientId() %>">修改 </a>   <a href="#">预约</a> </font>
</p>
<%} else {%>
<form action="patientServlet" method="post">
<table align="center" width="80%">
<tr>
	<th>病人编号</th>
	<th>家庭编号</th>
	<th>姓名</th>
	<th>性别</th>
</tr>
<tr>
	<td><input type="text" readonly="readonly" name="patientId" value="<%=p.getPatientId() %>"/></td>
	<td><input type="text" name="familyId" value="<%=p.getFamilyId() %>" /></td>
	<td><input type="text" name="name" value="<%=p.getName() %>" /></td>
	<td><input type="text" name="sex" value="<%=p.getSex() %>" /></td>
</tr>
<tr>
	<th>年龄</th>
	<th>邮箱</th>
	<th>电话</th>
	<th>住址</th>
</tr>
<tr>
	<td><input type="text" name="age" value="<%=p.getAge() %>" /></td>
	<td><input type="text" name="email" value="<%=p.getEmail() %>" /></td>
	<td><input type="text" name="tel" value="<%=p.getTel() %>" /></td>
	<td><input type="text" name="address" value="<%=p.getAddress() %>" /></td>
</tr>
</table>
<p align="center"> <input type="submit" value="提交" /> <input type="reset" value="重置"/></p>
<input type="hidden" name="op" value="update" />
</form>
<%} %>

<%
	AppointmentPageModel appm = (AppointmentPageModel)request.getAttribute("appm");
	int apn = appm.getApn();
	int aptn = appm.getAptn();
	List<Appointment> lap = appm.getLap();
%>
<h2 align="center"><font size="6" face="微软雅黑">预约信息表</font></h2>
<% if (aptn == 0) {%>
<p align="center"><font size="4" face="微软雅黑">暂无</font></p>
<%} else { %>
<table align="center" width="80%">
<tr>
	<th>预约编号</th>
	<th>牙医编号</th>
	<th>诊所编号</th>
	<th>日期</th>
	<th>状态</th>
</tr>
<%
	for (Appointment i : lap) {
%>
	<tr>
		<td><%=i.getApporintmentId() %></td>
		<td><%=i.getDentistId() %></td>
		<td><%=i.getClinicId() %></td>
		<td><%=i.getDate() %></td>
		<td>
		<% 
		if (i.getStatus() == 0) {%>
			等待
		<%} else {
			if (i.getStatus() == 1) {
			%>
			接受
			<% }else{ %>
			回绝
		<%}} %>
		</td>
	</tr>
<%} %>
</table>
<div align="center">
<font size="4" face="微软雅黑">
	<% if (apn > 1) {%>
	<a href="${pageContext.request.contextPath}/patientServlet?op=view&apn=<%=apn-1 %>&pid=<%=p.getPatientId() %>">上一页</a>
	<%}  
	for (int i = 1; i <= aptn; ++i) 
		if (i == apn) {%>
			<%=i %>
		<% } else {%>
			<a href="${pageContext.request.contextPath}/patientServlet?op=view&apn=<%=i %>&pid=<%=p.getPatientId() %>"><%=i %></a>
	
	
	<%} if (apn < aptn) {%>
	<a href="${pageContext.request.contextPath}/patientServlet?op=view&apn=<%=apn+1 %>&pid=<%=p.getPatientId() %>">下一页</a>
	<%} %>
</font>
</div>
<%} %>

<%
	PatientRecordPageModel prpm = (PatientRecordPageModel)request.getAttribute("prpm");
	int prn = prpm.getPrn();
	int prtn = prpm.getPrtn();
	List<PatientRecord> lpr = prpm.getLpr();
%>
<h2 align="center"><font size="6" face="微软雅黑">就诊信息表</font></h2>
<% if (prtn == 0) {%>
<p align="center"><font size="4" face="微软雅黑">暂无</font></p>
<%} else { %>
<table align="center" width="80%">
<tr>
	<th>就诊编号</th>
	<th>牙医编号</th>
	<th>操作</th>
</tr>
<%
	for (PatientRecord i : lpr) {
%>
	<tr>
		<td><%=i.getPatientRecordId() %></td>
		<td><%=i.getDentistId() %></td>
		<td>
			<a href="#">详细</a>
		</td>
	</tr>
<%} %>
</table>
<div align="center">
<font size="4" face="微软雅黑">
	<% if (prn > 1) {%>
	<a href="${pageContext.request.contextPath}/patientServlet?op=view&prn=<%=prn-1 %>&pid=<%=p.getPatientId() %>">上一页</a>
	<%}  
	for (int i = 1; i <= prtn; ++i) 
		if (i == prn) {%>
			<%=i %>
		<% } else {%>
			<a href="${pageContext.request.contextPath}/patientServlet?op=view&prn=<%=i %>&pid=<%=p.getPatientId() %>"><%=i %></a>
	
	
	<%} if (prn < prtn) {%>
	<a href="${pageContext.request.contextPath}/patientServlet?op=view&prn=<%=prn+1 %>&pid=<%=p.getPatientId() %>">下一页</a>
	<%} %>
</font>
</div>
<%} %>
</body>
</html>