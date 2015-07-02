<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,vo.*,java.util.Date" errorPage="" %>

<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<%

%>
<head>
<title>图书馆管理系统</title>
<link href="CSS/style.css" rel="stylesheet">
	<script language="javascript">
		function checkreader(form){
			if(form.barcode.value==""){
				alert("请输入读者条形码!");form.barcode.focus();return;
			}
			form.submit();
		}
	</script>
</head>
<body onLoad="clockon(bgclock)">
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" height="509"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="27" valign="top" style="padding:5px;" class="word_orange">&nbsp;当前位置：图书借还 &gt; 图书续借 &gt;&gt;&gt;</td>
  </tr>
  <tr>
    <td align="center" valign="top" style="padding:5px;"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="47" background="Images/borrowBackRenew_r.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="72" align="center" valign="top" background="Images/main_booksort_1.gif" bgcolor="#F8BF73"><table width="96%" border="0" cellpadding="1" cellspacing="1" bordercolor="#F8BF73">
          <tr>
            <td valign="top" bgcolor="#F8BF73">
            <%
	ArrayList allReaderinfo=(ArrayList)session.getAttribute("allReaderinfo");
	
	//ReaderInfo readerinfo =(ReaderInfo)allReaderinfo.get(0);
	if(allReaderinfo.isEmpty()){
%>
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
				<form name="form1" method="post" action="BorrowRenewServlet">
				
                  <tr>
                    <td><table width="90%" height="21" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="24%" height="18" style="padding-left:7px;padding-top:7px;"><img src="Images/reader_checkbg.jpg" width="142" height="18"></td>
                          <td width="76%" style="padding-top:7px;">读者编号：
                            <input name="barcode" type="text" id="barcode" value="" size="24">
                            &nbsp;
                            <input name="Button" type="button" class="btn_grey" value="确定" onClick="checkreader(form1)"></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="13" align="left" style="padding-left:7px;"><hr width="90%" size="1"></td>
                    </tr>
                  <tr>
                    <td align="center"><table width="96%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td height="27">姓&nbsp;&nbsp;&nbsp;&nbsp;名：
                            <input name="readername" type="text" id="readername" value=""></td>
                          
                          <td>读者类型：
                            <input name="readerType" type="text" id="readerType" value=""></td>
                        </tr>
                        <tr>
        
                          <td>证件号码：
                            <input name="paperNo" type="text" id="paperNo" value=""></td>
                          <td>可借数量：
                            <input name="number" type="text" id="number" value="" size="17">
                            册
                            &nbsp;</td>
                        </tr>
                    </table></td>
                  </tr>
				 </form>
              </table></td>
          </tr>
          <tr>
            <td valign="top"><table width="100%" height="35" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#F6B83B" bgcolor="#FFFFFF">
                <tr align="center" bgcolor="#e3F4F7">
                  <td width="24%" height="25" bgcolor="#FFF9D9">图书名称</td>
                  <td width="12%" bgcolor="#FFF9D9">借阅时间</td>
                  <td width="13%" bgcolor="#FFF9D9">应还时间</td>
                    <td width="13%" bgcolor="#FFF9D9">超期天数</td>
                  <td width="12%" bgcolor="#FFF9D9"><input name="Button22" type="button" class="btn_grey" value="完成续借" onClick="window.location.href='bookRenew.jsp'"></td>
                </tr>
<%}else{
	ReaderInfo readerinfo =(ReaderInfo)allReaderinfo.get(0);
 %>

                <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
				<form name="form1" method="post" action="BorrowRenewServlet">
				
                  <tr>
                    <td><table width="90%" height="21" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="24%" height="18" style="padding-left:7px;padding-top:7px;"><img src="Images/reader_checkbg.jpg" width="142" height="18"></td>
                          <td width="76%" style="padding-top:7px;">读者编号：
                            <input name="barcode" type="text" id="barcode" value="<%=readerinfo.getReaderid() %>" size="24">
                            &nbsp;
                            <input name="Button" type="button" class="btn_grey" value="确定" onClick="checkreader(form1)"></td>
                        </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="13" align="left" style="padding-left:7px;"><hr width="90%" size="1"></td>
                    </tr>
                  <tr>
                    <td align="center"><table width="96%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td height="27">姓&nbsp;&nbsp;&nbsp;&nbsp;名：
                            <input name="readername" type="text" id="readername" value="<%=readerinfo.getReadername()%>"></td>
                          
                          <td>读者类型：
                            <input name="readerType" type="text" id="readerType" value="<%=readerinfo.getRtypename()%>"></td>
                        </tr>
                        <tr>
        
                          <td>证件号码：
                            <input name="paperNo" type="text" id="paperNo" value="<%=readerinfo.getIdcard()%>"></td>
                          <td>可借数量：
                            <input name="number" type="text" id="number" value="<%=readerinfo.getNumber()%>" size="17">
                            册
                            &nbsp;</td>
                        </tr>
                    </table></td>
                  </tr>
				 </form>
              </table></td>
          </tr>
          <tr>
            <td valign="top"><table width="100%" height="35" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#F6B83B" bgcolor="#FFFFFF">
                <tr align="center" bgcolor="#e3F4F7">
                  <td width="24%" height="25" bgcolor="#FFF9D9">图书名称</td>
                  <td width="12%" bgcolor="#FFF9D9">借阅时间</td>
                  <td width="13%" bgcolor="#FFF9D9">应还时间</td>
                  <td width="13%" bgcolor="#FFF9D9">超期天数</td>

                
                  <td width="12%" bgcolor="#FFF9D9"><input name="Button22" type="button" class="btn_grey" value="完成续借" onClick="window.location.href='bookRenew.jsp'"></td>
                </tr>
                <%
                	ArrayList allBorrowBook=(ArrayList)session.getAttribute("allBorrowBook");
                                	for(int i=0;i<allBorrowBook.size();i++){
                                	BorrowInfo borrow=(BorrowInfo)allBorrowBook.get(i);
                %>
                <tr>
                  <td height="25" style="padding:5px;">&nbsp;<%=borrow.getBookname()%></td>
                  <td style="padding:5px;">&nbsp;<%=borrow.getBorrowdate() %></td>  
                  <td style="padding:5px;">&nbsp;<%=borrow.getOrderdate() %></td>
                  <% if(borrow.getOverdate()>0){%>
                  <td style="padding:5px;" >&nbsp;<font color="red">已超期<%=borrow.getOverdate() %>天</font></td>
                  <%}else{
                  	int overdate=Math.abs(borrow.getOverdate());
                  %>
                   <td style="padding:5px;" >&nbsp;<%=overdate %>天后超期</td>
                  <% }%>
                     <td width="12%" align="center"><a href="BorrowRenewOkServlet?borrowid=<%=borrow.getBookid()%>&barcode=<%=readerinfo.getReaderid()  %>">续借</a>&nbsp;</td>
                   </tr>
               <%
}}
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
