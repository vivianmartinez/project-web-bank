<%@include file="../header.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.entity.Entity"%>
<%@ page import="model.entity.Customer"%>
<main>
    <%@include file="../menu.jsp" %>
    <!-- Listado de clientes       -->
    <div class="container-md py-5">
        <table class="table table-hover">
            <thead>
                <th>Dni</th>
                <th>Nombre</th>
                <th>Ciudad</th>
                <th>Fecha Nacimiento</th>
                <th>Email</th>
            </thead>
            <tbody>
            <%
                ArrayList<Entity> customers = (ArrayList<Entity>) request.getAttribute("customers_list");
                for(int i = 0; i < customers.size();i++){
                    Customer c = (Customer) customers.get(i);
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

