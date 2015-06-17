<%@ page language="java" import="java.util.*,vo.*" pageEncoding="GB18030" contentType="text/html; charset=gb18030"%>
<% 
	    request.setCharacterEncoding("gb2312"); 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查询读者类型</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link href="CSS/style.css" rel="stylesheet">
  </head>
  
  <body>
  <%  
   		ArrayList allReaderType = (ArrayList)session.getAttribute("allReaderType");
  %>
     <%@include file="banner.jsp" %>
     <%@include file="navigation.jsp" %>
     <table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
         <td height="385" valign="top" bgcolor="#FFFFFF"><table width="99%" height="341"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
       <tr>
         <td height="341" valign="top" style="padding:5px;"><table width="98%" height="295"  border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td height="22" valign="top" class="word_orange">当前位置：读者管理 &gt; 读者类型设置 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td height="273" align="center" valign="top">
         <%
        	if(allReaderType.isEmpty()){
         %>
           <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="36" align="center">暂无读者类型信息！</td>
            </tr>
          </table>
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><a href="readertype_add.jsp">添加读者类型信息</a> </td>
  </tr>
</table>
          <%
        	}else{

  	        %>
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
           
         
          </tr>
          </table>

  <table width="91%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
<a href="readertype_add.jsp">添加读者类型信息</a> 

  <tr align="center" bgcolor="#e3F4F7">
    <td width="20%" bgcolor="#F9D16B">读者类型编号</td>
    <td width="30%" bgcolor="#F9D16B">读者类型名称</td>
    <td width="30%" bgcolor="#F9D16B">可借数量</td>
    <td width="14%" bgcolor="#F9D16B">删除</td>
  </tr>
<%
     for(int i=0;i<allReaderType.size();i++){
     ReaderType  readertype= (ReaderType)allReaderType.get(i);
    
    
	%> 
  <tr>
   <td style="padding:5px;"><%=readertype.getRtypeid() %></td>
    <td style="padding:5px;"><%=readertype.getRtypename() %></td>
     <td style="padding:5px;"><%=readertype.getNumber() %></td>
    <td align="center"><a href="ReaderTypeDeleteServlet?rtypeid=<%=readertype.getRtypeid() %>" >删除</a></td> 
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
