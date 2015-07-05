<%@ page language="java" import="java.util.*,vo.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="CSS/style.css" rel="stylesheet">
<script language="javascript">
			function prompt(){
			if(myform.limit.value==2){
				if(myform.count.value>0){
					alert("您有图书已到期，请及时归还！");
				}
			}else{
				return false;
			}
			}
</script>
  </head>
  
  <body onload="prompt()">
    <%@include file="banner.jsp" %>
    <%if(((int)session.getAttribute("limit"))==2){%>
	<%@include file="reader_navigation.jsp" %>
	<%}else{ %>
     <%@include file="navigation.jsp" %>
	<%} %>
	   <%
	
        	ArrayList allOverBook=(ArrayList)session.getAttribute("allOverBook");
        	   if(allOverBook!=null||!allOverBook.isEmpty()){
        	int  count=0;
        	for(int i=0;i<allOverBook.size();i++){
        	BorrowInfo borrow = (BorrowInfo)allOverBook.get(i);
        	if(borrow.getOverdate()>0){count++;}}
        	%>
        	<form name="myform">
        	<input name="limit" type="hidden"  value="<%=(Integer)session.getAttribute("limit") %>">
        	<input name="count"  type="hidden" value="<%=count%>">
        </form>
        <%} %>
  <table width="778" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td align="center" valign="top" style="padding:5px;"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="20" align="right" valign="middle" class="word_orange">当前位置：首页 &gt;&gt;&gt;&nbsp;</td>
      </tr>
      <tr>
        <td valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="57" background="Images/main_booksort.gif">&nbsp;</td>
          </tr>
          <tr>
            <td height="72" valign="top"><table width="100%" height="63"  border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="2%" rowspan="2">&nbsp;</td>
                <td width="96%" align="center" valign="top"><table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#B7B6B6" bordercolorlight="#FFFFFF">
                  <tr align="center">
                    <td width="5%" height="20">排名</td>
					<td width="10%">图书编号</td>
					<td width="15%">图书名称</td>
					<td width="10%">图书类型</td>
					<td width="9%">书架</td>
					<td width="14%">出版社</td>
					<td width="9%">现存量</td>
					<td width="11%">作者</td>
					<td>定价(元)</td>
				    <td  width="13%">借阅次数</td>
                  </tr>
					<% ArrayList allRankingList=(ArrayList)session.getAttribute("allRankingList");
						for(int i=0;i<allRankingList.size();i++){
						BookInfo bookinfo = (BookInfo)allRankingList.get(i);
						if(i<10){//排行榜前十名 排榜。
					%>
                  <tr>
                    <td height="25" align="center"><%=i+1%></td>
					<td style="padding:5px;"  align="center">&nbsp;<%=bookinfo.getBookid()%></td>
					<td style="padding:5px;"><%=bookinfo.getBookname() %></td>
					<td style="padding:5px;"><%=bookinfo.getBtypename() %></td>
					<td align="center">&nbsp;<%=bookinfo.getCname() %></td>
					<td align="center">&nbsp;<%=bookinfo.getPubname() %></td>
					<td width="11%" align="center"><%=bookinfo.getNownumber() %></td>
					<td width="8%" align="center"><%=bookinfo.getAuthor()%></td>
				    <td width="8%" align="center"><%=bookinfo.getPrice() %></td>
				    <td width="8%" align="center"><%=bookinfo.getTimes() %></td>
                  </tr>
				<%} }%>
                </table>
                  </td>
                <td width="2%" rowspan="2">&nbsp;</td>
              </tr>
              <tr>
                <td height="30" align="right" valign="middle"><a href=""><img src="Images/more.GIF" width="50" height="20" border="0">&nbsp;</a></td>
              </tr>
            </table></td>
          </tr>
        </table>
          </td>
      </tr>
    </table>
    </td>
  </tr>
</table>
<%@ include file="copyright.jsp"%>
  </body>
</html>
