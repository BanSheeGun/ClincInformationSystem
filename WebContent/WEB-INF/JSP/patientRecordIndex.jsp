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
<title>诊断信息</title>
</head>
<body>
<h1 align="center"><font size="8" face="微软雅黑"> 就诊记录  </font></h1>
<%
	PatientRecord pr = (PatientRecord)request.getAttribute("pr");
	Invoice inv = (Invoice)request.getAttribute("inv");
	Payment pay = (Payment)request.getAttribute("pay");
	String oper = (String)request.getAttribute("oper");
%>
<table align="center" width="80%">
<tr>
	<th>诊断编号</th>
	<th>牙医编号</th>
	<th>病人编号</th>
</tr>
<tr>
	<td><%=pr.getPatientRecordId() %></td>
	<td><%=pr.getDentistId() %></td>
	<td><%=pr.getPatientId() %></td>
</tr>
</table>

<%
	if (oper.equals("doc")) {
%>
<form action="patientRecordServlet" method="post">
<p align="center">
<textarea cols="128" rows="15" name="con">
<%=pr.getContent() %>
</textarea>
</p>
<p align="center"><input type="submit" value="提交"/> <input type="reset" value="重置"/></p>
<input type="hidden" name="op" value="update"/>
<input type="hidden" name="oper" value="<%=oper %>"/>
<input type="hidden" name="pid" value="<%=pr.getPatientRecordId() %>"/>
</form>
<% } else { %>
<p align="center">
<textarea readonly = "readonly" cols="128" rows="15">
<%=pr.getContent() %>
</textarea>
</p>
<%} %>
<h2 align="center"><font size="6" face="微软雅黑"> 支付信息  </font></h2>
<% if (pay != null) {%>
<table align="center" width="80%">
<tr>
	<th>支付编号</th>
	<th>病人编号</th>
	<th>金额</th>
</tr>
<tr>
	<td><%=pay.getPaymentId() %></td>
	<td><%=pay.getPatientId() %></td>
	<td><%=pay.getNumber() %></td>
</tr>
</table>
<% } else { %>
<h3 align="center"><font size="4" face="微软雅黑"> 暂无支付信息  </font></h3>
<% if (oper.equals("doc")) {%>
	<form action="patientRecordServlet" method="post">
		<p align="center">
		<input type="text" name="num" value="0"/>
		<input type="submit" value="提交订单"/></p>
		<input type="hidden" name="op" value="newpay"/>
		<input type="hidden" name="oper" value="doc"/>
		<input type="hidden" name="prid" value="<%=pr.getPatientRecordId() %>"/>
	</form>
<% 
	}
} %>
<h2 align="center"><font size="6" face="微软雅黑"> 发票信息  </font></h2>
<% if (inv != null) {%>
<table align="center" width="80%">
<tr>
	<th>发票编号</th>
	<th>病人编号</th>
	<th>金额</th>
	<th>支付日期</th>
</tr>
<tr>
	<td><%=inv.getInvoiceId() %></td>
	<td><%=inv.getPatientId() %></td>
	<td><%=inv.getNumber() %></td>
	<td><%=inv.getDate() %></td>
</tr>
</table>
<% } else { %>
<h3 align="center"><font size="4" face="微软雅黑"> 暂无发票信息  </font></h3>
<% if (oper.equals("pat") && pay != null) {%>
	<form action="patientRecordServlet" method="post">
		<p align="center"><input type="submit" value="申请发票"></p>
		<input type="hidden" name="op" value="newinv">
		<input type="hidden" name="oper" value="pat">
		<input type="hidden" name="prid" value="<%=pr.getPatientRecordId() %>">
		<input type="hidden" name="payid" value="<%=pay.getPaymentId() %>">
	</form>
<% }
} %>
</body>
</html>