<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'navigation.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="javascript">
			function quit(){
				if(confirm("真的要退出系统吗?")){
					window.location.href="logout.jsp";
				}
			}
		</script>
  </head>
  
  <body>
  <script src="JS/onclock.JS"></script>
<script src="JS/menu.JS"></script>
<div class=menuskin id=popmenu
      onmouseover="clearhidemenu();highlightmenu(event,'on')"
      onmouseout="highlightmenu(event,'off');dynamichide(event)" style="Z-index:100;position:absolute;"></div>
<table width="778"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr bgcolor="#DFA40C">
        <td width="3%" height="27">&nbsp;</td>
        <td width="29%"><div id="bgclock" class="word_white"></div></td>
		
        <td width="66%" align="right" bgcolor="#B0690B" class="word_white">
        <a href="main.jsp" class="word_white">首页</a>| 
     
        <a  onmouseover=showmenu(event,readermenu) onmouseout=delayhidemenu() style="CURSOR:hand"  class="word_white">读者管理</a> | 
        <a  onmouseover=showmenu(event,bookmenu) onmouseout=delayhidemenu() class="word_white" style="CURSOR:hand" >图书管理</a> |
        <a  onmouseover=showmenu(event,borrowmenu) onmouseout=delayhidemenu() class="word_white" style="CURSOR:hand">图书借还</a> | 
        <a  onmouseover=showmenu(event,querymenu) onmouseout=delayhidemenu()  class="word_white" style="CURSOR:hand" >系统查询</a> | 
   
        <a href="#" onClick="quit()" class="word_white">退出系统</a></td>
        
        <td width="2%" bgcolor="#B0690B">&nbsp;</td>
  </tr>
      <tr bgcolor="#DFA40C">
        <td height="9" colspan="4" background="Images/navigation_bg_bottom.gif"></td>
      </tr>
</table>

  </body>
</html>
