<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'banner.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
   <table width="778" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="118" valign="top" background="Images/top_bg.gif" bgcolor="#EEEEEE"><table width="100%" height="33"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="81%" height="10"></td>
        <td colspan="2"></td>
      </tr>
      <tr>
        <td height="20">&nbsp;</td>
        <td width="10%"><a href="#" onClick="window.location.reload();" class="word_dark">刷新页面</a></td>
        <td width="9%"><a href="#" onClick="myclose()" class="word_dark">关闭系统</a></td>
		
        </tr>
    </table>
      <table width="93%" height="79"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="69" align="right" valign="bottom">当前登录用户：<%=session.getAttribute("name")%></td>
        </tr>
    </table></td>
  </tr>
</table>
  </body>
</html>
