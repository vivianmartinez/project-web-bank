<%@include file="../header.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.entity.Entity"%>
<%@ page import="model.entity.Account"%>
<main>
    <%@include file="../menu.jsp" %>
    <!-- Listado de cuentas   -->
    <div class="container-md py-5">
        <table class="table table-hover">
            <thead>
                <th>Número cuenta</th>
                <th>Cliente Dni</th>
                <th>Cliente Nombre</th>
                <th>Balance</th>
                <th>Tipo cuenta</th>
                <th>Estado</th>
            </thead>
            <tbody>
            <%
                ArrayList<Entity> customers = (ArrayList<Entity>) request.getAttribute("account_list");
                for(int i = 0; i < customers.size();i++){
                   
                    String trow = "<tr><td>"+ c.getDni()+"</td><td>"+ c.getName()+" "+c.getLast_name()+"</td><td>"+ c.getCity()+"</td><td>"+c.getDate_birth()+"</td><td>"+ c.getEmail()+"</td></tr>";
                    out.print(trow);
                }
            %>
            </tbody>
        </table>
    </div>          
    <%@include file="../endcontent.jsp" %>
</main>
<%@include file="../footer.jsp" %> 