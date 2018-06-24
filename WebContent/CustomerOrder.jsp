<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.CustomOrder"%>

<!--
Author:
Name: Denis NIWEMUGISHA
Andrew ID:  dniwemug
 
On my honor, as a Carnegie-Mellon Rwanda student, 
I have neither given nor received unauthorized assistance on this work.
//-->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Order</title>
</head>
<body>



	<%
		// retrieving the pizzeria name
		ArrayList<String> pizzeria = (ArrayList) request.getSession().getAttribute("pizz");

		// the chosen option
		String option = request.getParameter("options");
		String size = request.getParameter("sizes");

		CustomOrder order = new CustomOrder();
		String clientOrder = order.doPricing(pizzeria, option, size);
		System.out.println(clientOrder);
	%>
	<h2>
		Your Order.
		<%=pizzeria.get(0)%>
		Pizzeria
	</h2>
	<IMG SRC="pizza.gif" ALT="Pizza" WIDTH=96 HEIGHT=96>

	<p>Dear customer we have received your order.</p>
	<p>
		<%=clientOrder%></p>

</body>
</html>



