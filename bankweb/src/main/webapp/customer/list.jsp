<%@include file="../header.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.entity.Entity"%>
<%@ page import="model.entity.Customer"%>
<main>
    <%@include file="../menu.jsp" %>

    <div>
        <form class="d-inline-flex col-6 my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Cliente" aria-label="Search" id="searchCustomer">
            <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Buscar</button>
        </form>
    </div>
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

