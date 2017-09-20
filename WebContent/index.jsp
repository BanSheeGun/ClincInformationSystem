<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

.cell{
          height: 50px;
          vertical-align: middle;
          display: inline-block;
          line-height: 40px;
    }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用诊所信息系统</title>
</head>
<body>
<br />
<br />
<br />
<p align="center" > <font size="10" face="微软雅黑">请登录系统 </font></p>

<br />
<br />
<div align="center">
<div class="cell">
<form action="loginServlet" method="post">
<table align="right">
<tr>
	<th>登录类型</th>
	<td><select name="type">
  	<option value ="family">  家庭  </option>
  	<option value ="patient">  病人  </option>
  	<option value="doctor">  医生  </option>
  	<option value="admin"> 管理员  </option>
	</select></td>
</tr>
<tr>
	<th>登录ID</th>
	<td> <input type="text" name="ID" value="在此输入"/> </td>
</tr>
<tr>
	<td> <input type="submit" value="登录"/> </td>
	<td> <input type="reset" value="重置"/>  </td>
</tr>
</table>
</form>
</div>

<div class="cell">
<font size="4" face="微软雅黑">
没有账号您可以注册<br />
<a href="${pageContext.request.contextPath}/patientServlet?op=new">一个病人账号</a>
或
<a href="${pageContext.request.contextPath}/dentistServlet?op=new">一个医生账号</a>
或
<a href="${pageContext.request.contextPath}/familyServlet?op=new">一个家庭账号</a>
</font>
</div>
</div>
</body>
</html>