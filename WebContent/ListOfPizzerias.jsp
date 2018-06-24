<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
ArrayList <String> listOfPizzeria = (ArrayList)request.getAttribute("list");
String field;
%>
<select name="Pizzeria">
        <option value="" selected>select a Pizzeria</option>
        <%
        for(int i=0;i<listOfPizzeria.size();i++) {
            field=listOfPizzeria.get(i).toString();
        %>
        <option value="<%=field%>"><%=field %></option>
        <%} %>
        
   </select>
      <button type="submit" value="Submit">Submit</button>



</body>
</html>