<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ing.mobile.stats.servlets.Controller" %>
<%@ page import="com.ing.mobile.model.dto.AndroidApplication" %>
<%@ page import="com.ing.mobile.model.dto.IOSApplication" %>
<%@ page import="com.ing.mobile.model.dto.WindowsPhoneApplication" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../../StaticStats/dojo-1.9.1/dijit/themes/claro/claro.css">
<link rel="stylesheet" href="../../StaticStats/dojo-1.9.1/dojox/grid/resources/Grid.css" />
<link rel="stylesheet" href="../../StaticStats/dojo-1.9.1/dojox/grid/resources/claroGrid.css" />
<link type="image/ico" rel="icon" href="../../StaticStats/icons/favicon_tcm7-34019.ico" />
<link type="image/ico" rel="shortcut icon" href="../../StaticStats/icons/favicon_tcm7-34019.ico" />
<link rel="stylesheet" href="../css/application.css">
<script>dojoConfig = {async: true, parseOnLoad: true, isDebug: true}</script>
<script src='../../StaticStats/dojo-1.9.1/dojo/dojo.js'></script>
<script>
	require(["dojo/parser", "dijit/MenuBar", "dijit/MenuBarItem", "dijit/PopupMenuBarItem","dijit/DropDownMenu", "dijit/MenuItem","dojox/grid/DataGrid","dojo/data/ItemFileWriteStore","dojo/dom","dojo/dom-construct"]);
</script>

<script>
<%
List<IOSApplication> iosApplications = (List<IOSApplication>)request.getAttribute(Controller.IOS_APP_ATTRIBUTE);
List<AndroidApplication> androidApplications = (List<AndroidApplication>)request.getAttribute(Controller.ANDROID_APP_ATTRIBUTE);
%>

 var DOMAINS = [
		{domain: "ios", description: "iOs",id:1}, 
		{domain: "android", description: "Android",id:2},
		{domain: "windows_phone", description: "Windows Phone",id:3}
 ];
 
 function getApplications(id) {
	 switch(id) {
		case DOMAINS[0].id://IOS
			return [
			<%for (int i = 0;i<iosApplications.size();i++) {%>
			 	<%out.print(i==0?"":",");%>{ id: "<%=iosApplications.get(i).getId()%>", description: "<%=iosApplications.get(i).getDescription()%>" }
			<%};%>
			];
		break;
		case DOMAINS[1].id://Android
			return [
			<%for (int i = 0;i<androidApplications.size();i++) {%>
			 	<%out.print(i==0?"":",");%>{ id: "<%=androidApplications.get(i).getId()%>", description: "<%=androidApplications.get(i).getAppDescription()%>" }
			<%};%>
			];
		break;
		default:
			return [];
	 }	
 };
 
 function getMaxNumApp() {
	 return Math.max(<%=androidApplications.size()%>,<%=iosApplications.size()%>);
 }
 </script>
<script src="../js/controller.js"></script>
<title>ING Mobile Stats</title>
</head>
<body class="claro">
    <div data-dojo-type="dijit/MenuBar" id="navMenu">
	    <div data-dojo-type="dijit/PopupMenuBarItem">
	        <span>Data</span>
	        <div data-dojo-type="dijit/DropDownMenu" id="dataMenu">
	            <div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){menuController(MENU_REFERENCE.DATA_APPLICATIONS);}">Applications</div>
	            <div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){menuController(MENU_REFERENCE.RD_IOS_RELEASE);}">Internal</div>
	        </div>
	    </div>
	    <div data-dojo-type="dijit/PopupMenuBarItem">
	        <span>Reports</span>
	        <div data-dojo-type="dijit/DropDownMenu" id="graphMenu">
	                <div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){alert('cut!')}">Averages</div>
	                <div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){alert('cut!')}">Stars</div>
	                <div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){alert('cut!')}">Comments</div>
	                <div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){alert('cut!')}">Releases</div>
	                <div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){alert('cut!')}">Snapshots</div>
	        </div>
	    </div>
	    <div data-dojo-type="dijit/PopupMenuBarItem">
	        <span>Reviews</span>
	        <div data-dojo-type="dijit/DropDownMenu" id="reviewsMenu">
	                <div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){alert('cut!')}">Report</div>
	                <div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){alert('cut!')}">Match</div>
	        </div>
	    </div>
	    <div data-dojo-type="dijit/PopupMenuBarItem">
	        <span>Configuration</span>
	        <div data-dojo-type="dijit/DropDownMenu" id="mailMenu">
	        		<div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){alert('cut!')}">Subscriptions</div>
	                <div data-dojo-type="dijit/MenuItem" data-dojo-props="onClick:function(){alert('cut!')}">Applications</div>
	        </div>
	    </div>
	</div>
	<div id="content" class="content">
	</div>
</body>
</html>