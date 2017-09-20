<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="entity.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
table, td, th
  {
  border:0px;
border-collapse:collapse;
padding: 5px 10px;
font-size: 16px;
font-family: Verdana;
color: black;
  text-align:center;
  }

th
  {
  background-color:black;
  color:white;
  }
tr {
background: #FFF;
  text-align:center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>家庭信息</title>
</head>
<body>
<%
	int pid = (int)request.getAttribute("pid");
%>
<h1 align="center"><font size="8" face="微软雅黑"> 创建预约  </font></h1>
<form action="appointmentServlet" method="post">
<table align="center" width="80%">
<tr>
	<th>预约编号</th>
	<th>病人编号</th>
	<th>牙医编号</th>
</tr>
<tr>
	<td><input type="text" readonly="readonly" name="appointmentId" value="自动生成"/></td>
	<td><input type="text" readonly="readonly" name="patientId" value="<%=pid %>"/></td>
	<td><input type="text" name="dentistId" value="在此输入" /></td>
</tr>
<tr>
	<th>诊所编号</th>
	<th>预约时间</th>
	<th>预约状态</th>
</tr>
<tr>
	<td><input type="text" name="clinicId" value="在此输入" /></td>
	<td><input type="text" readonly="readonly" name="date" value="自动生成"/></td>
	<td><input type="text" readonly="readonly" name="status" value="自动生成"/></td>
</tr>
</table>
<p align="center"> <input type="submit" value="提交" /> <input type="reset" value="重置"/></p>
<input type="hidden" name="op" value="create" />
</form>
</body>
</html>