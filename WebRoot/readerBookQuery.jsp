<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,vo.*" errorPage="" %>

<%@ page import="java.util.*"%>
<html>

<head>
<title>ͼ��ݹ���ϵͳ</title>
<link href="CSS/style.css" rel="stylesheet">
	<script language="javascript">
		function checkreader(form){
			if(form.barcode.value==""){
				alert("���������������!");form.barcode.focus();return;
			}
			form.submit();
		}
	</script>
</head>
<body onLoad="clockon(bgclock)">
<%@include file="banner.jsp"%>
<%@include file="reader_navigation.jsp"%>
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" height="558"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="27" valign="top" style="padding:5px;" class="word_orange">&nbsp;��ǰλ�ã�ͼ��軹 &gt; ͼ��黹 &gt;&gt;&gt;</td>
  </tr>
  <tr>
    <td align="center" valign="top" style="padding:5px;"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="47" background="Images/borrowBackRenew_back.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="72" align="center" valign="top" background="Images/main_booksort_1.gif" bgcolor="#F8BF73"><table width="96%" border="0" cellpadding="1" cellspacing="1" bordercolor="#F8BF73">
          <tr>
            <td valign="top" bgcolor="#F8BF73">

               
                    <td height="13" align="left" style="padding-left:7px;"><hr width="90%" size="1"></td>
                    </tr>
                  
                    <td align="center"><table width="96%" border="0" cellpadding="0" cellspacing="0"/>

              </table></td>
   
          <tr>
            <td valign="top"><table width="100%" height="35" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#F6B83B" bgcolor="#FFFFFF">
                <tr align="center" bgcolor="#e3F4F7">
                  <td width="24%" height="25" bgcolor="#FFF9D9">ͼ������</td>
                  <td width="12%" bgcolor="#FFF9D9">����ʱ��</td>
                  <td width="13%" bgcolor="#FFF9D9">Ӧ��ʱ��</td>
                  <td width="13%" bgcolor="#FFF9D9">��������</td>
                  <td width="13%" bgcolor="#FFF9D9">����</td>
                  <td width="14%" bgcolor="#FFF9D9">�ִ���</td>
                  <td width="12%" bgcolor="#FFF9D9">�����</td>          
                  </tr>
                <%
                	ArrayList allOverBook=(ArrayList)session.getAttribute("allOverBook");
                                	for(int i=0;i<allOverBook.size();i++){
                                	BorrowInfo borrow=(BorrowInfo)allOverBook.get(i);
                %>
                <tr>
                <input name="borrowid" type="hidden"  value="<%=borrow.getId() %>"/>
                  <td height="25" style="padding:5px;">&nbsp;<%=borrow.getBookname()%></td>
                  <td style="padding:5px;">&nbsp;<%=borrow.getBorrowdate() %></td>
                  <td style="padding:5px;">&nbsp;<%=borrow.getOrderdate() %></td>
                  <% if(borrow.getOverdate()>0){%>
                  <td style="padding:5px;" >&nbsp;<font color="red">�ѳ���<%=borrow.getOverdate() %>��</font></td>
                  <td style="padding:5px;">&nbsp;<font color="red"><%=borrow.getFine()%></font></td>
                  <%}else{
                  	int overdate=Math.abs(borrow.getOverdate());
                  	if(overdate>=0&&overdate<=10){
                  	
                  %>
                   <td style="padding:5px;" >&nbsp;<font color="blue"><%=overdate %>�����</font></td>
                  <td style="padding:5px;">&nbsp;<%=0.0%></td>
                  <% }else{%>
                   <td style="padding:5px;" >&nbsp;<%=overdate %>�����</td>
                  <td style="padding:5px;">&nbsp;<%=0.0%></td>
                  <% }}%>
                  <td align="center">&nbsp;<%=borrow.getNownumber() %></td>
                  <td align="center">&nbsp;<%=borrow.getTotal() %></td>
                  </tr>
      
                <%
}
%>
                
      
            </table>
			</td>
          </tr>
		 
        </table></td>
      </tr>
      <tr>
        <td height="19" background="Images/main_booksort_2.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>

    <%@ include file="copyright.jsp"%></td>
  </tr>
</table>
</body>
</html>
