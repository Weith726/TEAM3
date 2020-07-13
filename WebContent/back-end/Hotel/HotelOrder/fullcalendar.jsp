<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.HotelOrder.model.*"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="com.google.gson.JsonArray" %>
<%@ page import="com.google.gson.JsonElement" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>

<%
HotelOrderService hotelOrderSvc = new HotelOrderService();
List<HotelOrderVO> list = hotelOrderSvc.getFullCalendarInfo();
Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create();
String jsonStr = gson.toJson(list);
pageContext.setAttribute("list", jsonStr);
%>
<%
	java.util.GregorianCalendar time = new java.util.GregorianCalendar();
	long nowTime = time.getTimeInMillis();
	String S = new SimpleDateFormat("yyyy-MM-dd").format(nowTime);
	pageContext.setAttribute("time", S);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
<link href='fullcalendar/main.css' rel='stylesheet' />
<script src='fullcalendar/main.js'></script>
<script src='fullcalendar/locales-all.js'></script>
<script>

document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    now: '${time}' ,
    locale: 'zh-tw',
    editable: true, // enable draggable events
    aspectRatio: 2.3,
    scrollTime: '00:00', // undo default 6am scrollTime
    headerToolbar: {
      left: 'today prev,next',
      center: 'title',
      right: 'resourceTimelineDay,timeGridWeek,dayGridMonth'
    },
    initialView: 'dayGridMonth',
    views: {
      resourceTimelineThreeDays: {
        type: 'resourceTimeline',
        duration: { days: 3 },
        buttonText: '3 days'
      }
    },
    events: ${list}
    	
  });

  calendar.render();
});
</script>
<style>
 body {
    margin: 0;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #script-warning {
    display: none;
    background: #eee;
    border-bottom: 1px solid #ddd;
    padding: 0 10px;
    line-height: 40px;
    text-align: center;
    font-weight: bold;
    font-size: 12px;
    color: red;
  }

  #loading {
    display: none;
    position: absolute;
    top: 10px;
    right: 10px;
  }

  #calendar {
    max-width: 100%;
    max-height: 80%;
/*     margin-left:192px; */
    margin-top:10px;
  }

</style>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>
  <div id='calendar'></div>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>
</html>
