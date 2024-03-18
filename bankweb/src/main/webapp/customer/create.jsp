<%@include file="../header.jsp" %>
<% 
    String message = (String) request.getAttribute("message_create_customer");
%>
<main>
    <%@include file="../menu.jsp" %>
    <!-- form create customer -->
    <div class="container col-lg-6 mb-5 mb-lg-0 bg-light mt-5">
        <form class="row g-3" action="customercontroller" method="post">
        <div class="col-md-6">
            <label for="inputName" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="inputName" name="customerName">
        </div>
        <div class="col-md-6">
            <label for="inputLastName" class="form-label">Apellidos</label>
            <input type="text" class="form-control" id="inputLastName" name="customerLastName">
        </div>
        <div class="col-md-6">
            <label for="inputDni" class="form-label">Dni</label>
            <input type="text" class="form-control" id="inputDni" name="customerDni">
        </div>
        <div class="col-md-6">
            <label for="inputDateBirth" class="form-label">Fecha Nacimiento</label>
            <input type="date" class="form-control" id="inputDateBirth" name="dateBirth">
        </div>
        <div class="col-md-6">
            <label for="inputCity" class="form-label">Ciudad</label>
            <input type="text" class="form-control" id="inputCity" name="customerCity">
        </div>
        <div class="col-md-6">
            <label for="inputEmail" class="form-label">Email</label>
            <input type="email" class="form-control" id="inputEmail" name="customerEmail">
        </div>
        <div class="col-md-6">
            <label for="inputPassword" class="form-label">Password</label>
            <input type="password" class="form-control" id="inputPassword" name="customerPassword">
        </div>
        <div class="col-md-4">
            <label for="inputAccountType" class="form-label">Tipo de Cuenta</label>
            <select id="inputAccountType" class="form-select" name ="selectTypeAccount">
                <option value = "0" selected>Seleccione tipo</option>
                <option value = "2">Ahorro</option>
                <option value = "3">Corriente</option>
            </select>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Guardar</button>
        </div>
        </form>
        <%
            if(message != null){
        %>
        <div class="alert alert-secondary mt-3" role="alert">
        <%  
            out.print(message);
            message = null;
        %>
        </div>
        <%
            }
        %>
    </div>      
    <%@include file="../endcontent.jsp" %>
</main>
<%@include file="../footer.jsp" %> 