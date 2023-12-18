<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabeans.CustomerBean"></jsp:useBean>

<%
    String nextPage = "";
    String messageNextPage = "";

    customerBean.setEmailUser("");
    customerBean = null;
    nextPage = "/index.jsp";
%>

<jsp:forward page="<%=nextPage%>">
    <jsp:param value="<%=messageNextPage%>" name="message"/>
</jsp:forward>
