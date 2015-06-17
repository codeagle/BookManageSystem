<%@ page language="java" import="java.util.*,vo.*" pageEncoding="GB18030" contentType="text/html; charset=gb18030"%>
<% 
	    request.setCharacterEncoding("gb2312"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示出版社信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link href="CSS/style.css" rel="stylesheet">
  </head>
  
  <body>
  <%  
  		ArrayList allPublishing=(ArrayList)session.getAttribute("allPublishing");
  %>
     <%@include file="banner.jsp" %>
     <%@include file="navigation.jsp" %>
     <table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
         <td height="385" valign="top" bgcolor="#FFFFFF"><table width="99%" height="341"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
       <tr>
         <td height="341" valign="top" style="padding:5px;"><table width="98%" height="295"  border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td height="22" valign="top" class="word_orange">当前位置：系统设置 &gt; 出版社设置 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td height="273" align="center" valign="top">
         <%
         if(allPublishing.isEmpty()){
          	
         %>
           <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="36" align="center">暂无出版社信息！</td>
            </tr>
          </table>
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
      <a href="publishing_add.jsp">添加出版社信息</a> </td>
  </tr>
</table>
          <%
         	       }else{
  	        %>
  	        
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
           <td><a href="publishing_add.jsp" >添加出版社信息</a> </td>
         
          </tr>
          </table>
 
	  <table width="91%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
	  <tr align="center" bgcolor="#e3F4F7">
	    <td width="30%" bgcolor="#F9D16B">出版社编号</td>
	    <td width="40%" bgcolor="#F9D16B">出版社名称</td>
	    <td width="16%" bgcolor="#F9D16B">修改</td>
	    <td width="14%" bgcolor="#F9D16B">删除</td>
	  </tr>
<%
      for(int i=0;i<allPublishing.size();i++){
     	Publishing publishing=(Publishing)allPublishing.get(i);
	%> 
  <tr>
   <td style="padding:5px;"><%=publishing.getPubid()%></td>
    <td style="padding:5px;"><%=publishing.getPubname() %></td>
    <td align="center"><a href="PublishingServletByPubId?pubid=<%=publishing.getPubid() %>" >修改</a></td>
    <td align="center"><a href="PublishingDeleteServlet?pubid=<%=publishing.getPubid() %>" >删除</a></td> 
  </tr>
<%
 }}
%>  
 </table>
</td>
      </tr>
    </table>
</td>
  </tr>
</table><%@ include file="copyright.jsp"%></td>
  </tr>
</table>
  </body>
</html>
