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
<title>医生信息</title>
</head>
<body>
<h1 align="center"><font size="8" face="微软雅黑"> 牙医  </font></h1>
<%	
	String op = null;
	try {
		op = (String)request.getAttribute("op");
	} catch (Exception e) {
		op = request.getParameter("op");
	}
	
	Dentist d = (Dentist)request.getAttribute("d");
	if (op.equals("view")) {
%>
<table align="center" width="80%">
<tr>
	<th>医生编号</th>
	<th>诊所编号</th>
	<th>姓名</th>
	<th>性别</th>
</tr>
<tr>
	<td><%=d.getDentistId() %></td>
	<td><%=d.getClinicId() %></td>
	<td><%=d.getName() %></td>
	<td><%=d.getSex() %></td>
</tr>
<tr>
	<th>年龄</th>
	<th>邮箱</th>
	<th>电话</th>
	<th>住址</th>
</tr>
<tr>
	<td><%=d.getAge() %></td>
	<td><%=d.getEmail() %></td>
	<td><%=d.getTel() %></td>
	<td><%=d.getAddress() %></td>
</tr>
</table>

<p align="center">
<font size="4" face="微软雅黑"> 
<a href="${pageContext.request.contextPath}/dentistServlet?op=edit&did=<%=d.getDentistId() %>">修改 </a></font>
</p>
<%} else {%>
<form action="dentistServlet" method="post">
<table align="center" width="80%">
<tr>
	<th>医生编号</th>
	<th>诊所编号</th>
	<th>姓名</th>
	<th>性别</th>
</tr>
<tr>
	<td><input type="text" readonly="readonly" name="dentistId" value="<%=d.getDentistId() %>"/></td>
	<td><input type="text" name="clinicId" value="<%=d.getClinicId() %>" /></td>
	<td><input type="text" name="name" value="<%=d.getName() %>" /></td>
	<td><input type="text" name="sex" value="<%=d.getSex() %>" /></td>
</tr>
<tr>
	<th>年龄</th>
	<th>邮箱</th>
	<th>电话</th>
	<th>住址</th>
</tr>
<tr>
	<td><input type="text" name="age" value="<%=d.getAge() %>" /></td>
	<td><input type="text" name="email" value="<%=d.getEmail() %>" /></td>
	<td><input type="text" name="tel" value="<%=d.getTel() %>" /></td>
	<td><input type="text" name="address" value="<%=d.getAddress() %>" /></td>
</tr>
</table>
<p align="center"> <input type="submit" value="提交" /> <input type="reset" value="重置"/></p>
<input type="hidden" name="op" value="update" />
</form>
<% }%>


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
	<th>病人编号</th>
	<th>诊所编号</th>
	<th>日期</th>
	<th>状态</th>
</tr>
<%
	for (Appointment i : lap) {
%>
	<tr>
		<td><%=i.getApporintmentId() %></td>
		<td><%=i.getPatientId() %></td>
		<td><%=i.getClinicId() %></td>
		<td><%=i.getDate() %></td>
		<td>
		<% 
		if (i.getStatus() == 0) {%>
			<a href="${pageContext.request.contextPath}/appointmentServlet?op=update&status=1&did=<%=i.getDentistId() %>&aid=<%=i.getApporintmentId() %> "> 接受 </a>
			<a href="${pageContext.request.contextPath}/appointmentServlet?op=update&status=2&did=<%=i.getDentistId() %>&aid=<%=i.getApporintmentId() %> "> 拒绝 </a>
		<%} else {
			if (i.getStatus() == 1) {
			%>
			<a href="${pageContext.request.contextPath}/patientRecordServlet?op=new&did=<%=i.getDentistId() %>&aid=<%=i.getApporintmentId() %> "> 诊断  </a>
			<% }else{ %>
			已回绝
		<%}} %>
		</td>
	</tr>
<%} %>
</table>
<div align="center">
<font size="4" face="微软雅黑">
	<% if (apn > 1) {%>
	<a href="${pageContext.request.contextPath}/dentistServlet?op=view&apn=<%=apn-1 %>&did=<%=d.getDentistId() %>">上一页</a>
	<%}  
	for (int i = 1; i <= aptn; ++i) 
		if (i == apn) {%>
			<%=i %>
		<% } else {%>
			<a href="${pageContext.request.contextPath}/dentistServlet?op=view&apn=<%=i %>&did=<%=d.getDentistId() %>"><%=i %></a>
	
	
	<%} if (apn < aptn) {%>
	<a href="${pageContext.request.contextPath}/dentistServlet?op=view&apn=<%=apn+1 %>&did=<%=d.getDentistId() %>">下一页</a>
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
	<th>病人编号</th>
	<th>操作</th>
</tr>
<%
	for (PatientRecord i : lpr) {
%>
	<tr>
		<td><%=i.getPatientRecordId() %></td>
		<td><%=i.getPatientId() %></td>
		<td>
			<a href="${pageContext.request.contextPath}/patientRecordServlet?oper=doc&op=view&prid=<%=i.getPatientRecordId() %>">详细</a>
		</td>
	</tr>
<%} %>
</table>
<div align="center">
<font size="4" face="微软雅黑">
	<% if (prn > 1) {%>
	<a href="${pageContext.request.contextPath}/dentistServlet?op=view&prn=<%=prn-1 %>&did=<%=d.getDentistId() %>">上一页</a>
	<%}  
	for (int i = 1; i <= prtn; ++i) 
		if (i == prn) {%>
			<%=i %>
		<% } else {%>
			<a href="${pageContext.request.contextPath}/dentistServlet?op=view&prn=<%=i %>&did=<%=d.getDentistId() %>"><%=i %></a>
	
	
	<%} if (prn < prtn) {%>
	<a href="${pageContext.request.contextPath}/dentistServlet?op=view&prn=<%=prn+1 %>&did=<%=d.getDentistId() %>">下一页</a>
	<%} %>
</font>
</div>
<%} %>
</body>
</html>