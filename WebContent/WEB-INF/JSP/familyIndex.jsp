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
<title>家庭信息</title>
</head>
<body>
<h1 align="center"><font size="8" face="微软雅黑"> 家庭  </font></h1>
<%
	String op = (String)request.getAttribute("op");
	Family f = (Family)request.getAttribute("f");
	PatientPageModel ppm = (PatientPageModel)request.getAttribute("ppm");
	List<Patient> lp = ppm.getLp();
	if (op.equals("view")) {
%>
<table align="center" width="80%">
<tr>
	<th>编号</th>
	<th>地址</th>
	<th>操作</th>
</tr>
<tr>
	<td><%=f.getFamilyId() %></td>
	<td><%=f.getAddress() %></td>
	<td><a href="${pageContext.request.contextPath}/familyServlet?op=edit&fid=<%=f.getFamilyId() %>"> 修改 </a></td>
</tr>
</table>
<% } else { %>
<form action="familyServlet" method="post">
	<table align="center" width="80%">
<tr>
	<th>编号</th>
	<th>地址</th>
	<th>操作</th>
</tr>
<tr>
	<td><input type="text" readonly="readonly" name="fid" value="<%=f.getFamilyId() %>"/></td>
	<td><input type="text" name="add" value="<%=f.getAddress() %>"/></td>
	<td><input type="submit" value="提交" /></td>
</tr>
</table>
<input type="hidden" name="op" value="update" />
</form>
<%} %>
<h2 align="center"><font size="6" face="微软雅黑"> 家庭成员表 </font></h2>
<% if (!(lp == null || lp.isEmpty())) {%>
<table align="center" width="80%">
<tr>
	<th>病人编号</th>
	<th>姓名</th>
	<th>年龄</th>
	<th>性别</th>
	<th>电话</th>
	<th>邮箱</th>
	<th>地址</th>
</tr>
<% for (Patient p : lp) { %>
<tr>
	<td> <%=p.getPatientId() %> </td>
	<td> <%=p.getName() %> </td>
	<td> <%=p.getAge() %> </td>
	<td> <%=p.getSex() %> </td>
	<td> <%=p.getTel() %> </td>
	<td> <%=p.getEmail() %> </td>
	<td> <%=p.getAddress() %> </td>
</tr>
<% } %>
</table>
<%} else { %>
<p align="center"><font size="4" face="微软雅黑"> 暂无数据 </font></p>
<%} %>
</body>
</html>