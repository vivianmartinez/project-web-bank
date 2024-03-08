<%@include file="header.jsp" %>
<main>
    <% String email_user = (String) session.getAttribute("email");
    out.print(email_user);
    %>
    <%@include file="menu.jsp" %>
    Home
    <%@include file="endcontent.jsp" %>
</main>
<%@include file="footer.jsp" %>