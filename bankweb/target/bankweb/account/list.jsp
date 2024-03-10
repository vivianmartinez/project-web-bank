<%@include file="../header.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="model.entity.Entity"%>
<%@ page import="model.entity.Account"%>
<main>
    <%@include file="../menu.jsp" %>
    <!-- Listado de cuentas   -->
    <div class="container-md py-5">
        <table class="table table-hover">
            <thead>
                <th>Cuenta</th>
                <th>Cliente Dni</th>
                <th>Cliente Nombre</th>
                <th>Tipo cuenta</th>
                <th>Saldo</th>
                <th>Estado</th>
                <th>Fecha creación </th>
            </thead>
            <tbody>
            <%
                ArrayList<HashMap> accounts = (ArrayList<HashMap>) request.getAttribute("account_list");
                HashMap element = new HashMap();
                for(int i = 0; i < accounts.size();i++){
                    element = accounts.get(i);
                    String status_account = element.get("active").toString().equals("1") ? "activa" : "inactiva";
                    //creamos filas de la tabla
                    String trow = "<tr><td>" + element.get("account_number").toString() +"</td><td>"+ element.get("dni").toString() +"</td><td>"+ element.get("name").toString() + " " + element.get("last_name").toString() + "</td><td>"+ element.get("type_account").toString() +"</td><td>"+ element.get("balance").toString() +"</td><td>"+  status_account +"</td><td>"+  element.get("created_at").toString() + "</td></tr>";
                    out.print(trow); 
                }
            %>
            </tbody>
        </table>
    </div>          
    <%@include file="../endcontent.jsp" %>
</main>
<%@include file="../footer.jsp" %> 