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
<h1 align="center"><font size="8" face="微软雅黑"> 创建新家庭  </font></h1>
<form action="familyServlet" method="post">
	<table align="center" width="80%">
<tr>
	<th>编号</th>
	<th>地址</th>
	<th>操作</th>
</tr>
<tr>
	<td><input type="text" readonly="readonly" name="fid" value="将自动生成"/></td>
	<td><input type="text" name="add" value="在此输入地址"/></td>
	<td><input type="submit" value="提交" /></td>
</tr>
</table>
<input type="hidden" name="op" value="create" />
</form>
</body>
</html>