<%@page import="dao.factory.DAOFactory"%>
<%@ page language="java" import="java.util.*,vo.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title>图书馆管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link href="CSS/style.css" rel="stylesheet">
  <script language="javascript">
		function checkreader(form){
			if(form.readerNo.value==""){
				alert("请输入读者编号!");form.readerNo.focus();return;
			}
			form.submit();
		}
		
		function checkbook(form){
			if(form.readerNo.value==""){
				alert("请输入读者编号!");form.readerNo.focus();return;
			}
			if(form.inputkey.value==""){
				alert("请输入查询关键字!");form.isbn.focus();return;
			}
			if(form.countnum.value==form.number.value){
				alert("借阅已达上限，不可借阅。");form.inputkey.focus();return;
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
    <td valign="top" bgcolor="#FFFFFF">
    <table width="100%" height="509"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="27" valign="top" style="padding:5px;" class="word_orange">当前位置：图书借还 &gt; 图书借阅 &gt;&gt;&gt;</td>
  </tr>
  <tr>
    <td align="center" valign="top" style="padding:5px;">
    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<form name="form1" method="post" action="BorrowServlet">
      <tr>
        <td height="47" background="Images/borrowBackRenew.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="72" align="center" valign="top" background="Images/main_booksort_1.gif" bgcolor="#F8BF73">
        <table width="96%" border="0" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#F8BF73">
          <tr>
            <td valign="top" bgcolor="#F8BF73">        
        <%
        	
        	ArrayList allBorrowReader=(ArrayList)session.getAttribute("allBorrowReader");
        		if(allBorrowReader.isEmpty()){
         %>
         <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
		
				
                  <tr>
                    <td><table width="90%" height="21" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="24%" height="18" style="padding-left:7px;padding-top:7px;"><img src="Images/reader_checkbg.jpg" width="142" height="18"></td>
                          <td width="76%" style="padding-top:7px;">读者编号：
                            <input name="readerNo" type="text" id="readerNo"  value="" size="24">
                            &nbsp;
                            <input name="Button" type="button" class="btn_grey"  value="确定"  onClick="checkreader(form1)"></td>
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
                            <input name="rtypename" type="text" id="rtypename" value=""></td>
                          <td>可借数量：
                            <input name="number1" type="text" id="number1" value="" size="17">
                            册&nbsp;</td>
                        </tr>
                        <tr>
                          <td>证件编号：
                            <input name="idcard" type="text" id="1" value=""></td>      
                            <td>已借数量：
                            <input name="borrownumber" type="text" id="borrownumber" value="" size="17">
                            册&nbsp;</td> 
                        </tr>
                    </table>
                  
					<tr>
					<tr>
                 <td height="32" background="Images/borrow_if.gif">&nbsp;添加的依据：
                   <input name="f" type="radio" class="noborder" value="barcode" checked>
                   图书编号 &nbsp;&nbsp;
                   
					<input name="inputkey" type="text" id="inputkey" size="50">
                   <input name="Submit2" type="button" class="btn_grey" value="确定" onClick="checkbook(form1)">
                   <input name="operator" type="hidden" id="operator" value="<%%>">
  <input name="Button" type="button" class="btn_grey" value="完成借阅" onClick="window.location.href='book_borrow.jsp'">
                   </td>
               </tr> 
            <td valign="top" bgcolor="#FCEC9A" style="padding:5px"><table width="99%" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#F6B83B" bgcolor="#FFFFFF">
                   <tr align="center" bgcolor="#F9D16B">
                     <td width="29%" height="25">图书名称</td>
                     <td width="12%">借阅时间</td>
                     <td width="14%">应还时间</td>
                     <td width="17%">现存量</td>
                     <td width="14%">库存量</td>
                   </tr>
                      
    </tr>
                    </td></tr></table></td></tr></table></td></tr></form></table></td></tr></table></td></tr></table>
                    <%}else{%>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			<% ArrayList allReader =(ArrayList)session.getAttribute("allBorrowReader");
				ReaderInfo readerinfo=(ReaderInfo)allReader.get(0);
				%>
                  <tr>
                    <td><table width="90%" height="21" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="24%" height="18" style="padding-left:7px;padding-top:7px;"><img src="Images/reader_checkbg.jpg" width="142" height="18"></td>
                          <td width="76%" style="padding-top:7px;">读者编号：
                            <input name="readerNo" type="text" id="readerNo"  value="<%=readerinfo.getReaderid() %>" size="24">
                            &nbsp;
                            <input name="Button" type="button" class="btn_grey"  value="确定"  onClick="checkreader(form1)"></td>
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
                            <input name="readername" type="text" id="readername" value="<%=readerinfo.getReadername() %>"></td>
                          <td>读者类型：
                            <input name="rtypename" type="text" id="rtypename" value="<%=readerinfo.getRtypename() %>"></td>
                          <td>可借数量：
                            <input name="number" type="text" id="number" value="<%=readerinfo.getNumber() %>" size="17">
                            册&nbsp;</td>
                        </tr>
                        <tr>
                          <td>证件编号：
                            <input name="idcard" type="text" id="1" value="<%=readerinfo.getIdcard() %>"></td>
                            <td>已借数量：
                            <input name="borrownumber" type="text" id="borrownumber" value="<%=readerinfo.getBorrownumber() %>" size="17">
                            册&nbsp;</td>       
                        </tr>
                    </table>
                  
					<tr>
					<tr>
                 <td height="32" background="Images/borrow_if.gif">&nbsp;添加的依据：
                   <input name="f" type="radio" class="noborder" value="barcode" checked>
                   图书编号 &nbsp;&nbsp;
                   
					<input name="inputkey" type="text" id="inputkey" size="50">
					<%int countnum =(int)session.getAttribute("countnum"); %> 
					<input name="countnum" type="hidden" value="<%=countnum%>">
					<%System.out.println("规定的数量"+readerinfo.getNumber()); %>
					<%System.out.println("已借阅的数量"+session.getAttribute("countnum") ); %>
                   <input name="Button2"  type="button"  class="btn_grey"   value="确定"   onClick="checkbook(form1)">
                   <input name="operator" type="hidden" id="operator" value="<%%>">
  <input name="Button" type="button" class="btn_grey" value="完成借阅" onClick="window.location.href='book_borrow.jsp'">
                   </td>
               </tr> 
            <td valign="top" bgcolor="#FCEC9A" style="padding:5px"><table width="99%" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolorlight="#FFFFFF" bordercolordark="#F6B83B" bgcolor="#FFFFFF">
                   <tr align="center" bgcolor="#F9D16B">
                     <td width="29%" height="25">图书名称</td>
                     <td width="12%">借阅时间</td>
                     <td width="14%">应还时间</td>
                     <td width="17%">现存量</td>
                     <td width="14%">库存量</td>
                   </tr>
                   <%
                   	ArrayList allBorrowBook =(ArrayList)session.getAttribute("allBorrowBook");
                                      for(int i=0;i<allBorrowBook.size();i++){
                                     BorrowInfo borrow=(BorrowInfo)allBorrowBook.get(i);
                   %>
			   <td style="padding:5px;"><%=borrow.getBookname()%></td>
			    <td style="padding:5px;"><%=borrow.getBorrowdate() %></td>
			    <td style="padding:5px;"><%=borrow.getOrderdate()%></td>
			    <td style="padding:5px;"><%=borrow.getNownumber()%></td>
			    <td style="padding:5px;"><%=borrow.getTotal()%></td>
 </tr>
    <%} %>
   
    
                    </td></tr></table></td></tr></table></td></tr></form></table></td></tr></table></td></tr></table>
                    <%} %>
</body>
</html>
