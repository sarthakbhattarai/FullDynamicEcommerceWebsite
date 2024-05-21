<%@page import="com.entity.viewlist"%>
<%@page import="com.dao.DAO2"%>
<%@page import="com.conn.DBConnect"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="customer_navbar.jsp" %>

<%
    String query = request.getParameter("query");
    DAO2 dao = new DAO2(DBConnect.getConn());
    List<viewlist> relatedProducts = dao.searchProducts(query);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Search Results</title>
    <!-- Add your head content here -->
</head>
<body>
    <div style="background-color: #ebe9eb">
        <br>
        <h1>Search Results for <%= query %></h1>
        <br>
    </div>
    <br>

    <div class="container">
        <div class="row">
            <% if (relatedProducts != null && !relatedProducts.isEmpty()) { %>
                <% for (viewlist product : relatedProducts) { %>
                <div class="col-xxl-3 col-xl-3 col-lg-3 col-md-4 col-sm-6 col-xs-12 border">
                    <div class="container" style="background-color: white">
                        <center>
                            <table>
                                <tr>
                                    <th>
                                        <a href="selecteditemc.jsp?Pn=<%=product.getImage()%>">
                                            <img src="images/<%=product.getImage()%>" height="150px" width="150px">
                                        </a>
                                    </th>
                                </tr>
                                <tr style="background-color: #ebe9eb">
                                    <th style="text-align: center">
                                        <a href="selecteditemc.jsp?Pn=<%=product.getImage()%>">
                                            <%=product.getBname()%> <%=product.getPname()%>
                                        </a>
                                    </th>
                                </tr>
                            </table>
                        </center>
                    </div>
                </div>
                <% } %>
            <% } else { %>
                <div>No products found for <%= query %></div>
            <% } %>
        </div>
    </div>
</body>
</html>