<%@include file="header.jsp" %>
<%@ page import="model.entity.User"%>
<main>
    <% User  currentUser = (User) session.getAttribute("currentUser");
    out.print(currentUser.getName());
    %>
    <%@include file="menu.jsp" %>
    Home
    <%@include file="endcontent.jsp" %>
</main>
<%@include file="footer.jsp" %>